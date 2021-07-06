package com.github.yaohui.gateway.filter;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.github.yaohui.common.constants.CommonConstants;
import com.github.yaohui.common.context.BaseContextHandler;
import com.github.yaohui.common.entity.UserInfo;
import com.github.yaohui.common.rest.CommonResponse;
import com.github.yaohui.common.utils.JWTHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashSet;
import java.util.List;

@Configuration
@Slf4j
public class AccessGatewayFilter implements GlobalFilter {

    @Value("${gate.ignore.startWith}")
    private String startWith;

    private static final String GATE_WAY_PREFIX = "/api";

    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange, GatewayFilterChain gatewayFilterChain) {
        log.info("check token and user permission....");
        LinkedHashSet requiredAttribute = serverWebExchange.getRequiredAttribute(ServerWebExchangeUtils.GATEWAY_ORIGINAL_REQUEST_URL_ATTR);
        ServerHttpRequest request = serverWebExchange.getRequest();
        // 获取当前网关访问的URI
        String requestUri = request.getPath().pathWithinApplication().value();
        for (URI next : (Iterable<URI>) requiredAttribute) {
            if (next.getPath().startsWith(GATE_WAY_PREFIX)) {
                requestUri = next.getPath().substring(GATE_WAY_PREFIX.length());
            }
        }
        final String method = request.getMethod().toString();
        BaseContextHandler.setToken(null);
        ServerHttpRequest.Builder mutate = request.mutate();
        // 网关不进行拦截的URI配置，常见如验证码、Login接口
        if (isStartWith(requestUri)) {
            ServerHttpRequest build = mutate.build();
            return gatewayFilterChain.filter(serverWebExchange.mutate().request(build).build());
        }
        UserInfo user = null;
        try {
            // 判断用户token，获取用户信息
            user = getJWTUser(request, mutate);
        } catch (Exception e) {
            log.error("用户Token过期异常", e);
            return getVoidMono(serverWebExchange, new CommonResponse(CommonConstants.ERROR_401, "User Token Error or Expired!"), HttpStatus.UNAUTHORIZED);
        }
        ServerHttpRequest build = mutate.build();
        return gatewayFilterChain.filter(serverWebExchange.mutate().request(build).build());
    }

    /**
     * 网关抛异常
     */
    @NotNull
    private Mono<Void> getVoidMono(ServerWebExchange serverWebExchange, CommonResponse errorMessage, HttpStatus status) {
        serverWebExchange.getResponse().setStatusCode(status);
        byte[] bytes = JSONObject.toJSONString(errorMessage).getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = serverWebExchange.getResponse().bufferFactory().wrap(bytes);
        return serverWebExchange.getResponse().writeWith(Flux.just(buffer));
    }

    /**
     * 解析token获取用户信息
     * 支持header、url中传入token
     *
     * @param request
     * @param ctx
     * @return
     */
    private UserInfo getJWTUser(ServerHttpRequest request, ServerHttpRequest.Builder ctx) throws Exception {

        List<String> strings = request.getHeaders().get(CommonConstants.TOKEN_HEADER);
        String authToken = null;
        if (strings != null) {
            authToken = strings.get(0);
        }
        if (StrUtil.isBlank(authToken)) {
            strings = request.getQueryParams().get("token");
            if (strings != null) {
                authToken = strings.get(0);
            }
        }
        UserInfo infoFromToken = JWTHelper.getInfoFromToken(authToken);
        ctx.header(CommonConstants.TOKEN_HEADER, authToken);
        BaseContextHandler.setToken(authToken);
        return infoFromToken;
    }


    /**
     * URI是否以什么打头
     *
     * @param requestUri
     * @return
     */
    private boolean isStartWith(String requestUri) {
        boolean flag = false;
        for (String s : startWith.split(",")) {
            if (requestUri.startsWith(s)) {
                return true;
            }
        }
        return flag;
    }


}

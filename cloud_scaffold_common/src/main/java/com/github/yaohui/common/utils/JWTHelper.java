package com.github.yaohui.common.utils;

import com.github.yaohui.common.constants.CommonConstants;
import com.github.yaohui.common.entity.UserInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.joda.time.DateTime;

public class JWTHelper {

    private static final String PRIVATE_KEY ="jwtk";
    private static final int EXPIRE = 24 * 60 * 60 * 1000;

    /**
     * 密钥加密token
     */
    public static String generateToken(UserInfo userInfo) throws Exception {
        return Jwts.builder()
                .setSubject(userInfo.getUserId())
                .claim(CommonConstants.JWT_KEY_USER_ID, userInfo.getUserId())
                .claim(CommonConstants.JWT_KEY_NAME, userInfo.getUserName())
                .setExpiration(DateTime.now().plusSeconds(EXPIRE).toDate())
                .signWith(SignatureAlgorithm.HS512, PRIVATE_KEY.getBytes())
                .compact();
    }

    public static void main(String[] args) throws Exception {
        UserInfo userInfo = UserInfo.builder().userId("admin").userName("管理员").build();
        System.out.println(JWTHelper.generateToken(userInfo));
        System.out.println(parserToken(JWTHelper.generateToken(userInfo)));
        System.out.println(getInfoFromToken(JWTHelper.generateToken(userInfo)));

    }

    /**
     * 公钥解析token
     *
     * @param token
     * @return
     * @throws Exception
     */
    public static Jws<Claims> parserToken(String token) throws Exception {
        return Jwts.parser().setSigningKey(PRIVATE_KEY.getBytes()).parseClaimsJws(token);
    }
    /**
     * 获取token中的用户信息
     *
     */
    public static UserInfo getInfoFromToken(String token) throws Exception {
        if(token.startsWith("Bearer")){
            token = token.replace("Bearer ","");
        }
        Jws<Claims> claimsJws = parserToken(token);
        Claims body = claimsJws.getBody();
        return new UserInfo(body.getSubject(), String.valueOf(body.get(CommonConstants.JWT_KEY_USER_ID)));
    }
}

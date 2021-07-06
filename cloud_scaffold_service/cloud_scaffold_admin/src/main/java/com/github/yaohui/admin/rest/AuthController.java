package com.github.yaohui.admin.rest;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.yaohui.admin.entity.CloudUser;
import com.github.yaohui.admin.form.UserLoginForm;
import com.github.yaohui.admin.service.CloudUserPermissionRelService;
import com.github.yaohui.admin.service.CloudUserService;
import com.github.yaohui.common.config.CommonException;
import com.github.yaohui.common.constants.CommonConstants;
import com.github.yaohui.common.entity.UserInfo;
import com.github.yaohui.common.rest.BaseController;
import com.github.yaohui.common.rest.CommonResponse;
import com.github.yaohui.common.utils.JWTHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Slf4j
@RequestMapping("/rest/auth")
public class AuthController extends BaseController {

    @Autowired
    private CloudUserPermissionRelService cloudUserPermissionRelService;

    @Autowired
    private CloudUserService cloudUserService;

    @RequestMapping("/getAllPermission/{userId}")
    public CommonResponse getAllPermissions(@PathVariable String userId){
        return buildReturnSuccess(cloudUserPermissionRelService.getAllPermissionsByUserId(userId));
    }

    /**
     * 登录接口
     * @param userLoginForm
     * @return
     * @throws Exception
     */
    @PostMapping("/login")
    public CommonResponse login(@RequestBody UserLoginForm userLoginForm) throws Exception {

        LambdaQueryWrapper<CloudUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CloudUser::getUserId, userLoginForm.getUserId()).eq(CloudUser::getPassword, userLoginForm.getPassword());
        List<CloudUser> userList = cloudUserService.getMapper().selectList(queryWrapper);
        if (userList == null || userList.size() != 1){
            throw new CommonException(CommonConstants.ERROR_CODE, "账号密码错误");
        }
        CloudUser user = userList.get(0);
        UserInfo userInfo = UserInfo.builder().userId(user.getUserId()).userName(user.getUserName()).build();
        String token = JWTHelper.generateToken(userInfo);
        return buildReturnSuccess(token);
    }

}

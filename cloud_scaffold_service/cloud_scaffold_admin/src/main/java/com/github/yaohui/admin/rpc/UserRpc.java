package com.github.yaohui.admin.rpc;

import com.github.yaohui.admin.service.CloudUserPermissionRelService;
import com.github.yaohui.common.entity.PermissionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/rpc/user")
@RestController
public class UserRpc {

    @Autowired
    private CloudUserPermissionRelService cloudUserPermissionRelService;

    @RequestMapping(value = "/allPermissions/{userId}", method = RequestMethod.GET)
    public @ResponseBody
    List<PermissionInfo> getPermissionByUsername(@PathVariable("userId") String userId) {
        return cloudUserPermissionRelService.getAllPermissionsByUserId(userId);
    }
}

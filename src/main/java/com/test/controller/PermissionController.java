package com.test.controller;

import com.test.model.Permission;
import com.test.service.IPermissionService;
import com.test.util.JsonResponseBody;
import com.test.util.JsonResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author herixin
 * @create 2022-12-15 14:36
 */
@ResponseBody
@RequestMapping("/permission")
@CrossOrigin
public class PermissionController {
    @Autowired
    private IPermissionService iPermissionService;
    @RequestMapping("/menuList")
    public JsonResponseBody<List<Permission>> bookList() {
        List<Permission> permissions = iPermissionService.selectMenu(null);
        if (permissions == null || permissions.size() <= 0) {
            return new JsonResponseBody<>(JsonResponseStatus.ERROR.getCode(),JsonResponseStatus.ERROR.getMsg());
        }
        return new JsonResponseBody<>(JsonResponseStatus.SUCCESS.getCode(),JsonResponseStatus.SUCCESS.getMsg(),permissions);
    }
}

package com.test.service.impl;

import com.test.mapper.PermissionMapper;
import com.test.model.Permission;
import com.test.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.applet.Main;

import java.util.List;

/**
 * @author herixin
 * @create 2022-12-15 14:27
 */
@Service
public class PermissionServiceImpl implements IPermissionService {
    @Autowired
    private PermissionMapper permissionMapper;
    @Override
    public List<Permission> selectMenu(Integer perid) {
//        System.out.println("perid = " + perid);
        List<Permission> list = permissionMapper.selectMenu(perid);
        for (Permission permission : list) {
            List<Permission> list1 = selectMenu(permission.getPerid());
            permission.setList(list1);
            if (list1.size() <= 0) {
                return list;
            }
        }
        return list;
    }
}

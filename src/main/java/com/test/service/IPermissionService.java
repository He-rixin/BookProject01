package com.test.service;

import com.test.model.Permission;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * @author herixin
 * @create 2022-12-15 14:26
 */
public interface IPermissionService {
    @Cacheable("PermissionList")
    List<Permission> selectMenu(Integer perid);
}

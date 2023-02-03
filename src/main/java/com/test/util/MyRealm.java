package com.test.util;

import com.test.mapper.UserMapper;
import com.test.model.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * @author herixin
 * @create 2022-12-14 11:24
 */
public class MyRealm extends AuthorizingRealm {
    @Autowired
    private UserMapper userMapper;

    /**
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        获取用户传递过来的账号
        String username = principalCollection.getPrimaryPrincipal().toString();
        //获取用户角色和权限
        Set<String> roles = userMapper.findRoles(username);
        Set<String> permission = userMapper.findPermission(username);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
        info.setStringPermissions(permission);
        return info;
    }

    /**
     * 身份验证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取用户传过来的信息
        Object username = authenticationToken.getPrincipal();
        Object password = authenticationToken.getCredentials();
        User user = new User();
        user.setUsername(username.toString());
        user.setPassword(password.toString());
        User u = userMapper.login(user);
        if (u == null) {
            throw new RuntimeException("账号不存在");
        }
        //将数据库中获取的账号密码保存到realm中
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
                u.getUsername(),
                u.getPassword(),
                ByteSource.Util.bytes(u.getSalt()),
                this.getName()
        );

        return info;
    }
}

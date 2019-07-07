package com.zm.zmtools.config;


import com.zm.zmtools.pojo.Permission;
import com.zm.zmtools.pojo.Role;
import com.zm.zmtools.pojo.User;
import com.zm.zmtools.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName CustomRealm
 * @Description 这个类很关键
 * @Author zhaoluowei
 * @Date 2019/7/5  23:10
 * @Version 1.0
 */

@Slf4j
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 授权时认证  看用户是否具有 对应的权限
     * 进行权限校验时调用
     *
     * @param principal
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        log.info("授权 doGetAuthorizationInfo ");
        System.out.println("授权 doGetAuthorizationInfo ");

        User newUser = (User) principal.getPrimaryPrincipal();

        User user = userService.findAllUserInfoByUsername(newUser.getUsername());
        List<String> roleList = new ArrayList<>();
        List<String> permissionList = new ArrayList<>();

        List<Role> userRoleList = user.getRoleList();
        for (Role r : userRoleList) {
            roleList.add(r.getName());
            //角色对应的权限
            List<Permission> permissions = r.getPermissionList();
            for (Permission p : permissions) {
                if (p != null) {
                    permissionList.add(p.getName());
                }
            }
        }

        //授权用的
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addRoles(roleList);
        authorizationInfo.addStringPermissions(permissionList);
        return authorizationInfo;
    }

    /**
     * 用户登录调用
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("认证 doGetAuthenticationInfo ");
        System.out.println("认证 doGetAuthenticationInfo ");
        //从token中获取用户名,token代表用户输入
        String username = (String) authenticationToken.getPrincipal();

        //调用service 进行判断  返回的user 对象也需要进行判断。
        User allUserInfo = userService.findAllUserInfoByUsername(username);
        if (allUserInfo != null) {
            String password = allUserInfo.getPassword();
            if (password == null || "".equals(password)) {
                return null;
            }
            //返回 认证通过
            //return new SimpleAuthenticationInfo(username, allUserInfo.getPassword(), this.getClass().getName());
            //用到了redis的缓存
            return new SimpleAuthenticationInfo(allUserInfo, allUserInfo.getPassword(), this.getClass().getName());

        }
        return null;

    }


}

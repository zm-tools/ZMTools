package com.zm.zmtools.filter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * @ClassName CustomRolesAuthorizationFilter
 * @Description 自定义 filter
 * @Author zhaoluowei
 * @Date 2019/7/7  0:12
 * @Version 1.0
 */

public class CustomRolesAuthorizationFilter extends AuthorizationFilter {


    @SuppressWarnings({"unchecked"})
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {

        Subject subject = getSubject(request, response);
        System.err.println("CustomRolesAuthorizationFilter ");
        //获取当前访问路径所需要的角色集合
        String[] rolesArray = (String[]) mappedValue;

        //没有角色限制，可以直接访问
        if (rolesArray == null || rolesArray.length == 0) {
            //no roles specified, so nothing to check - allow access.
            return true;
        }

        Set<String> roles = CollectionUtils.asSet(rolesArray);
        System.err.println("CustomRolesAuthorizationFilter 中的角色 "+roles);
        //当前subject是roles 中的任意一个，则有权限访问
        for(String role : roles){
            if(subject.hasRole(role)){
                return true;
            }
        }

        return false;
    }


//    @Override
//    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
//        Subject subject = this.getSubject(request, response);
//        System.err.println("CustomRolesAuthorizationFilter ");
//        //获取当前访问路径需要的 角色集合
//        String[] rolesArray = (String[]) ((String[]) mappedValue);
//        //没有角色限制
//        if (rolesArray != null && rolesArray.length != 0) {
//            return true;
//        }
//
//        Set<String> roles = CollectionUtils.asSet(rolesArray);
//        //只要有其中一个权限  就能访问
//        for (String s : roles) {
//            if (subject.hasRole(s)) {
//                return true;
//            }
//        }
//
//        return false;
//    }
}

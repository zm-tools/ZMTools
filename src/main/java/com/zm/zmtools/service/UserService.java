package com.zm.zmtools.service;

import com.zm.zmtools.pojo.User;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author zhaoluowei
 * @Date 2019/7/4  23:40
 * @Version 1.0
 */

public interface UserService {

    /**
     * 获取全部用户信息，包括角色，权限
     * @param username
     * @return
     */
    User findAllUserInfoByUsername(String username);


    /**
     * 获取用户基本信息
     * @param userId
     * @return
     */
    User findSimpleUserInfoById(int userId);


    /**
     * 根据用户名查找用户信息
     * @param username
     * @return
     */
    User findSimpleUserInfoByUsername(String username);

}

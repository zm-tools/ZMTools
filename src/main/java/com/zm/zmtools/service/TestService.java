package com.zm.zmtools.service;

import java.util.List;

import com.zm.zmtools.entity.TblUserInfo;
import com.zm.zmtools.pojo.AddUserInfo;

public interface TestService {
    /**
     * 获取所有用户信息
     * @return
     */
    List<TblUserInfo> findAllUserInfoService()throws Exception;

    int addUserInfoService(AddUserInfo userInfo)throws Exception;
}

package com.zm.zmtools.service;

import java.util.List;

import com.zm.zmtools.entity.TblUserInfo;

public interface TestService {
    /**
     * 获取所有用户信息
     * @return
     */
    List<TblUserInfo> findAllUserInfoService();
}

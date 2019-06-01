package com.zm.zm_tools.service;

import com.zm.zm_tools.entity.TblUserInfo;

import java.util.List;

public interface TestService {
    /**
     * 获取所有用户信息
     * @return
     */
    List<TblUserInfo> findAllUserInfoService();
}

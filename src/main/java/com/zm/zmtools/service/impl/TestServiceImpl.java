package com.zm.zmtools.service.impl;


import com.zm.zmtools.pojo.AddUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zm.zmtools.entity.TblUserInfo;
import com.zm.zmtools.mapper.TblUserInfoMapper;
import com.zm.zmtools.service.TestService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TblUserInfoMapper tblUserInfoMapper;

    @Override
    public List<TblUserInfo> findAllUserInfoService() {
        return this.tblUserInfoMapper.selectList(null);
    }

    @Override
    @Transactional
    public int addUserInfoService(AddUserInfo userInfo) throws Exception {
        int update = this.tblUserInfoMapper.insertUserInfoMapper(userInfo);
        //int a =1/0;
        return update;
    }
}

package com.zm.zmtools.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zm.zmtools.entity.TblUserInfo;
import com.zm.zmtools.mapper.TblUserInfoMapper;
import com.zm.zmtools.service.TestService;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TblUserInfoMapper tblUserInfoMapper;

    @Override
    public List<TblUserInfo> findAllUserInfoService() {
        return this.tblUserInfoMapper.selectList(null);
    }
}

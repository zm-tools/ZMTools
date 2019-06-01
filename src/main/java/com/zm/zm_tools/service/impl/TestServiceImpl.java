package com.zm.zm_tools.service.impl;


import com.zm.zm_tools.entity.TblUserInfo;
import com.zm.zm_tools.mapper.TblUserInfoMapper;
import com.zm.zm_tools.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

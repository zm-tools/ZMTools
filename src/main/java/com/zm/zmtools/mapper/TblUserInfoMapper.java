package com.zm.zmtools.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zm.zmtools.entity.TblUserInfo;

import com.zm.zmtools.pojo.AddUserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TblUserInfoMapper extends BaseMapper<TblUserInfo> {

    @Insert("INSERT INTO tbl_userinfo(username,phone,age) VALUES(#{userInfo.username},#{userInfo.phone},#{userInfo.age})")
    int insertUserInfoMapper(@Param("userInfo") AddUserInfo userInfo)throws Exception;
}

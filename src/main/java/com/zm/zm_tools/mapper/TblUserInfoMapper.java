package com.zm.zm_tools.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zm.zm_tools.entity.TblUserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TblUserInfoMapper extends BaseMapper<TblUserInfo> {

}

package com.zm.zmtools.mapper;

import com.zm.zmtools.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @ClassName UserMapper
 * @Description TODO
 * @Author zhaoluowei
 * @Date 2019/7/3  23:14
 * @Version 1.0
 */

@Mapper
@Repository
public interface UserMapper {

    @Select("SELECT id,username,password,create_time,salt FROM tbl_user WHERE username=#{username}")
    User findByUsername(@Param("username") String username);

    @Select("SELECT id,username,create_time,salt FROM tbl_user WHERE id=#{id}")
    User findById(@Param("id") Integer id);


    @Select("SELECT id,username,create_time,salt FROM tbl_user WHERE username = #{username} AND password = #{password}")
    User findByUsernameAndPwd(@Param("username") String username,
                              @Param("password") String password);

}

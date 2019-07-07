package com.zm.zmtools.mapper;

import com.zm.zmtools.pojo.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName PermissionMapper
 * @Description TODO
 * @Author zhaoluowei
 * @Date 2019/7/4  0:16
 * @Version 1.0
 */
@Mapper
@Repository
public interface PermissionMapper {
    //根据角色找权限集合
    @Select("SELECT p.`name`,p.url,p.id \n" +
            "FROM tbl_role_permission rp \n" +
            "LEFT JOIN tbl_permission p ON rp.permission_id = p.id\n" +
            "WHERE rp.role_id = #{roleId}")
    List<Permission> findPermissionListByRoleId(@Param("roleId") Integer roleId);

}

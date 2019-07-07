package com.zm.zmtools.mapper;

import com.zm.zmtools.pojo.Role;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName RoleMapper
 * @Description TODO
 * @Author zhaoluowei
 * @Date 2019/7/3  23:48
 * @Version 1.0
 */

@Mapper
@Repository
public interface RoleMapper {

    @Select("SELECT r.role_id AS id ,ro.`name` AS `name`,ro.description AS description\n" +
            "FROM  tbl_user_role r LEFT JOIN tbl_role ro ON r.role_id = ro.id\n" +
            "WHERE r.user_id = #{userId}")
    @Results(
            value = {
                    @Result(id=true, property = "id",column = "id"),
                    @Result(property = "name",column = "name"),
                    @Result(property = "description",column = "description"),
                    @Result(property = "permissionList",column = "id",
                            // 引用写好的 SQL
                            many = @Many(select = "com.zm.zmtools.mapper.PermissionMapper.findPermissionListByRoleId", fetchType = FetchType.DEFAULT)
                    )
            }
    )
    List<Role> findRoleListByUserId(@Param("userId") Integer userId);
}

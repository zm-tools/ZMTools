package com.zm.zmtools.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zm.zmtools.entity.TblUserRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 赵洛伟
 * @since 2019-07-07
 */
@Mapper
@Repository
public interface TblUserRoleMapper extends BaseMapper<TblUserRole> {

}

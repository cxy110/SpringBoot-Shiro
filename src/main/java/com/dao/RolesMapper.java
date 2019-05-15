package com.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.entity.Roles;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author CXY
 * @since 2019-05-07
 */
public interface RolesMapper extends BaseMapper<Roles> {
  //添加
  //然后基于角色id查找权限标识信息
  List<String> findPermissions(@Param("roleIds") Integer[] integers);
}

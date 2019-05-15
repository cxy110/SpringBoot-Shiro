package com.dao;

import com.entity.UserRoles;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author CXY
 * @since 2019-05-07
 */
public interface UserRolesMapper {
 int  insertuser (UserRoles userRoles);
 Integer selectroleId(Integer userId);
 List<Integer> selectRoleIdByUserId(Integer userId); //添加
}
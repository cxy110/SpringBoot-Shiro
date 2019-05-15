package com.dao;

import com.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author CXY
 * @since 2019-05-07
 */
public interface UserMapper extends BaseMapper<User> {
 String selectname(String name);
User  selectok(@Param("username") String username, @Param("md5pass") String md5pass);
void update(Integer userId);
User selectall(String username);
}

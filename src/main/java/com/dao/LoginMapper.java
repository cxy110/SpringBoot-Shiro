package com.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.entity.User;

public interface LoginMapper extends BaseMapper<User> {
  int selectname(String username);

}

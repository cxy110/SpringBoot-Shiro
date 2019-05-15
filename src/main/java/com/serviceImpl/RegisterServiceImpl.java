package com.serviceImpl;

import com.dao.RegisterMapper;
import com.dao.UserMapper;
import com.dao.UserRolesMapper;
import com.entity.User;
import com.entity.UserRoles;
import com.service.RegisterService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class RegisterServiceImpl implements RegisterService {
  @Autowired
  public RegisterMapper registerMapper;
  @Autowired
  public UserRolesMapper userRolesMapper;
  @Autowired
 public UserMapper userMapper;
  @Override
  public Integer registeruser(String username, String password) {
    if(StringUtils.isEmpty(username))
      throw new RuntimeException("用户名不能为空");
    if(StringUtils.isEmpty(password))
      throw new RuntimeException("密码不能为空");
     String md5pass=DigestUtils.md5Hex(password);
    User user=new User();
    user.setUsername(username);
    user.setPassword(md5pass);
    user.setValid(1);

    Integer row= registerMapper.insert(user);
    User userget=userMapper.selectok(username,md5pass);
Integer x=userget.getId();
    UserRoles userRoles=new UserRoles();
    userRoles.setUserId(userget.getId());
    userRoles.setRoleId(1);


     userRolesMapper.insertuser(userRoles);
  ;
    return row;
  }
}

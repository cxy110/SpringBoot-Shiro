package com.controller;

import com.entity.User;
import com.shiro.UserShiroRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogoutController {
  @Autowired
  private UserShiroRealm userShiroRealm;
  @PostMapping(value = "/logout")
  public String logout(){
    Subject subject = SecurityUtils.getSubject();
    User user = (User) subject.getPrincipal();
    userShiroRealm.logout();
    return "欢迎您的再次光临";
  }
}

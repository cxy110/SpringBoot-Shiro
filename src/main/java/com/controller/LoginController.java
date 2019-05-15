package com.controller;

import com.entity.JsonResult;
import com.entity.UserBack;
import com.service.LoginService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/login")
public class LoginController {
  @Autowired
  private LoginService loginService;
  @PostMapping("/lo/{username}/{password}")

  public JsonResult login(@PathVariable String username, @PathVariable String password,HttpServletRequest request, HttpServletResponse response){

    /**
     * 用户登录时获取用户的信息到shiro
     *
     */
    //获取subject
    Subject subject= SecurityUtils.getSubject();
    //查看用户是否已经登录
    if(!subject.isAuthenticated()) {
      //封装用户信息
      String md5pass = DigestUtils.md5Hex(password);
      UsernamePasswordToken token = new UsernamePasswordToken(username, md5pass);
      //remeberme
     token.setRememberMe(true);

      //提交用户信息,执行登录方法
      try {
        subject.login(token);
      } catch (UnknownAccountException e) {
        throw new RuntimeException("用户名不存在");
      } catch (IncorrectCredentialsException e) {
        throw new RuntimeException("密码错误");
      }
    }

    UserBack userBack= loginService.login(username,password, request , response);
    JsonResult jsonResult=new JsonResult(userBack);

    return jsonResult;
  }
  @PostMapping("/hello")
  public  String hello(){
    return  "helloworld";
  }

}

package com.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.config.TokenAuto;
import com.dao.UserMapper;
import com.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticationInterceptor implements HandlerInterceptor {
  @Autowired
  UserMapper userMapper;
  @Autowired
  TokenAuto tokenAuto;
  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

  }

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    Subject subject= SecurityUtils.getSubject();
    User user=(User)subject.getPrincipal();
   if(user==null){
     throw new RuntimeException("请先登录");
   }
    String toke=null;
    Cookie[] cookiess=request.getCookies();
    for(Cookie cookie:cookiess){
      if("token".equals(cookie.getName())){
        toke=cookie.getValue();
      }
    }
if(toke==null){
  throw new RuntimeException("无token,请重新登录,401");

}
//获取保存在shiro的session中的数据
    Subject cubject= SecurityUtils.getSubject();
User u=  (User) cubject.getPrincipal();
  Session session=cubject.getSession();
 String secret =(String) session.getAttribute("token");
 JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secret)).build();
 try {
   jwtVerifier.verify(toke);

 }catch (JWTVerificationException e){
   throw new RuntimeException("token无效");
 }
    //通过httpservletsession 和httpservletsession来实现验证token
 /* HttpSession session= request.getSession(false);
String result= (String) session.getAttribute(toke);
if(result==null){
  throw new RuntimeException("token无效");
  */



    return true;
  }

}

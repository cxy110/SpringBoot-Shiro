package com.serviceImpl;

import com.config.TokenAuto;
import com.dao.LoginMapper;
import com.dao.UserMapper;
import com.dao.UserRolesMapper;
import com.entity.User;
import com.entity.UserBack;
import com.service.LoginService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Service
public class LoginServiceImp implements LoginService {
  @Value("${token.time}")
int tokentime;
@Autowired
private LoginMapper loginMapper;
@Autowired
private UserMapper userMapper;
@Autowired
private UserRolesMapper userRolesMapper;
@Autowired
TokenAuto tokenAuto;
  @Override
  public UserBack login(String username, String password, HttpServletRequest request, HttpServletResponse response) {

    //String name = userMapper.selectname(username);

  // if (name ==null)
   //   throw new RuntimeException("用户名错误");
   String md5pass= DigestUtils.md5Hex(password);
   // User user=new User();

  User usersess=  userMapper.selectok(username,md5pass);




 //if(usersess==null)
 //  throw new RuntimeException("密码错误");
   int userId=usersess.getId();

   //修改最后登录时间
    userMapper.update(userId);
    //生成token
String tokens= tokenAuto.getToken(username,md5pass);

   //通过shiro会话管理的session保存生成的token
    Subject subject = SecurityUtils.getSubject();
   Session session= subject.getSession();
   session.setAttribute("token",md5pass);
       //失效时间
   session.setTimeout(tokentime*1000);//毫秒

    Cookie cookie=new Cookie("token",tokens);
    cookie.setMaxAge(tokentime);
    cookie.setPath("/");
    response.addCookie(cookie);


   //通过httpservletsession 和httpservletcookie以及拦截器进行token验证

 /*  // HttpSession session= request.getSession();
   // session.setAttribute("tokeing",tokens);
   // session.setMaxInactiveInterval(tokentime);

    Cookie cookie=new Cookie("token","tokeing");
    cookie.setMaxAge(tokentime);
    cookie.setPath("/");
    response.addCookie(cookie);

    */




    //准备返回信息
     Integer roleId=userRolesMapper.selectroleId(userId);
    UserBack userBack=new UserBack();
    userBack.setUser(usersess);
    userBack.setRoleId(roleId);
    userBack.setToken(tokens);
    userBack.setTokentime(new Date(System.currentTimeMillis()+tokentime));

      return userBack;
  }
}

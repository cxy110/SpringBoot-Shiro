package com.config;

import com.entity.JsonResult;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;



/**
 * @ControllerAdvice此注解描述的类称之为全局异常处理类
 * @author cgb
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {
  /**
   * @exceptionHandler 对象描述的方法为一个异常
   *  @exceptionHandler 内部定义的异常处理为
   */
  @ExceptionHandler(Exception.class)
  @ResponseBody
  public JsonResult doHandleException(RuntimeException e)
  {
    System.out.println("GlobalExceptionHandler");
    e.printStackTrace();
    int code=401;
    return new JsonResult(e ,code);//封装异常
  }

  @ExceptionHandler(ShiroException.class)
  @ResponseBody
  public JsonResult doHandleShiroException(
      ShiroException e){
    e.printStackTrace();
    JsonResult A=new JsonResult();

    if(e instanceof IncorrectCredentialsException){
     A.NOZG("密码不正确",401);
    }else if(e instanceof AuthorizationException) {
      A.NOZG("没有此操作权限",401);}
      else if(e instanceof UnknownAccountException){
        A.NOZG("账号不存在",401);
    }else if(e instanceof UnauthorizedException) {
      A.NOZG("没有此操作权限",401);}


    return A;
    }


  }








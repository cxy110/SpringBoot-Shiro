package com.shiro;

import org.apache.shiro.session.mgt.ExecutorServiceSessionValidationScheduler;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

@Configuration
public class ShiroConfig {
  /**
   * 创建shiroFilterFactoryBean
   */
  @Bean
  public ShiroFilterFactoryBean newShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
    ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
    //设置安全管理器
    bean.setSecurityManager(securityManager);
    //设置拦截后跳转的路径
    bean.setLoginUrl("/login/lo");
 /* shiro内置过滤器,实现权限相关拦截
 常用过滤器:
 *  anon:无需认证(登录),就可访问
 *  logout:登出
 *  authc 必须认证才能访问
 *  user:如果使用了 remeber me 功能可以直接访问
 *  perms:该资源必须得到资源权限才能访问
 *  role:该资源必须得到角色权限才能访问
 *  port:拦截指定端口的访问路径,指定端口才能通过
 *  ssl:必须是https请求
 * */
    LinkedHashMap<String, String> fcMap =
        new LinkedHashMap<>();
// fcMap.put("/login/hello","authc");
    // fcMap.put("login/lo","authc");//不能做anon,authc的权限设置,不然在登录时机会出问题
    // fcMap.put("/free/freeone","authc");
    // fcMap.put("/login/hello","authc");
    // fcMap.put("/logout","logout");
    fcMap.put("/free/freeone", "perms[user:free]");
    fcMap.put("/free/freeone", "user");

    fcMap.put("/free/freetwo", "perms[user:free,user:login]");
    //授权过滤器
//设置没有权限后,跳转的提示页面
    bean.setUnauthorizedUrl("/nn/noZK");

    bean.setFilterChainDefinitionMap(fcMap);
    return bean;
  }

  /**
   * 创建defaultwebsecurityManage
   */
  @Bean("securityManager")
  public DefaultWebSecurityManager newDefaultWebSecurityManager(UserShiroRealm userShiroRealm) {
    DefaultWebSecurityManager sManager =
        new DefaultWebSecurityManager();
    //此时必须保证realm对象已经存在了(关联realm)
    sManager.setRealm(userShiroRealm);
    sManager.setRememberMeManager(RememberMeManager());
    sManager.setSessionManager(sessionManager());

    return sManager;
  }

  /**
   * 创建Realm对象
   */

  public UserShiroRealm getRealm() {
    return new UserShiroRealm();
  }

  public CookieRememberMeManager RememberMeManager() {
    CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
    cookieRememberMeManager.setCookie(RemebermesimpleCookie());
    return cookieRememberMeManager;
  }

  //remeberme
  public SimpleCookie RemebermesimpleCookie() {
    SimpleCookie simpleCookie = new SimpleCookie("remeberMe");
    simpleCookie.setHttpOnly(true);
    simpleCookie.setMaxAge(20);
    return simpleCookie;
  }

  //sessionmanager 会话管理
  @Bean
  public SessionManager sessionManager() {
    DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();

    sessionManager.setSessionValidationScheduler(sessionValidationScheduler());

    return sessionManager;
  }

  //session的调度器,定时对过期或无效的session进行清理
  public ExecutorServiceSessionValidationScheduler sessionValidationScheduler() {
   ExecutorServiceSessionValidationScheduler executorServiceSession=new ExecutorServiceSessionValidationScheduler();
   executorServiceSession.setInterval(1800000L); //毫秒
   return executorServiceSession;
  }
}






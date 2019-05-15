package com.shiro;
/**
 * 自定义reaml
 */

import com.alibaba.druid.util.StringUtils;
import com.dao.RolesMapper;
import com.dao.UserMapper;
import com.dao.UserRolesMapper;
import com.entity.Bmd;
import com.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@ConfigurationProperties(prefix = "")
public class UserShiroRealm extends AuthorizingRealm {
  @Autowired
  private UserMapper userMapper;
  @Autowired
  private UserRolesMapper userRolesMapper;
  @Autowired
  private RolesMapper rolesMapper;
  @Autowired
  private Bmd bmd;

  /**
   * 执行认证逻辑
   *
   * @param authenticationToken
   * @return
   * @throws AuthenticationException
   */
  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
    //获取用户的登录信息,参数转换成传入进来时的状态
    UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
    String username = upToken.getUsername();
    // String name = userMapper.selectname(username);
    User user = userMapper.selectall(username);
    //判断用户名
    if (user == null)
      return null;
//判断密码
    //第一个参数:用户的实体信息,可以是username,也可以是数据表对应的用户的实体类对象,这个值必须是唯一的
    //第二个参数是从数据库中查到的用户的密码
    //第三个参数:realmName是当前reaml的name,调用父类的getName()方法即可,或者不写

    return new SimpleAuthenticationInfo(user, user.getPassword(), "");

    //如果是通过盐值来进行密码的验证,(盐值可以保证在数据库中的加密后密码不一样) 如下
    //在注册时输入的密码然后生成一个随机数作为盐值,然后通过盐值和MD5对密码加密,把加密后的密码和盐值存入数据库
     //String salt=UUID.randomUUID().toString();//生成一个随机数,然后变成字符串
    //		entity.setSalt(salt);
    //		//通过盐值对密码加密
    //		SimpleHash sHash=new SimpleHash("MD5",entity.getPassword(), salt);

    // ByteSource credentialsSalt=ByteSource.Util.bytes(user.getSalt());

    //第一个参数和上面第一个一样, 第二个是数据库中查出的盐值加密的密码,第三个是处理后的盐值(如上一行),
    // 第四个数据就是realmname,这样 下面这个方法就会把传进来的密码,通过盐值加密后与数据库里的比较
   //登录进来的在UsernamePasswordToken中的密码就是用户传进来的,不是加密处理后的.
    // return  new SimpleAuthenticationInfo(Object principal, Object hashedCredentials,
     //   ByteSource credentialsSalt,
     //   String realmName)


  }

  /**
   * 执行授权逻辑
   *
   * @param principalCollection
   * @return
   */
  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
    //获取当前登录用户
    Subject subject = SecurityUtils.getSubject();
    User user = (User) subject.getPrincipal();
       //下面这一行也是获取当前对象
       //User user=(User) principalCollection.getPrimaryPrincipal()
    //通过用户id查角色id
    List<Integer> roleIds = userRolesMapper.selectRoleIdByUserId(user.getId());
    if (roleIds == null || roleIds.size() == 0)
      throw new AuthorizationException(); //此处抛出的异常在全局异常中捕获,表明没有任何权限
    //通过角色id查权限
    Integer[] array = {};
    List<String> permissionList = rolesMapper.findPermissions(roleIds.toArray(array));
    if (permissionList == null || permissionList.size() == 0)
      throw new AuthorizationException();
    //将权限标识封装在set集合中
    Set<String> pSet =
        new HashSet<String>();
    for (String permission : permissionList) {
      if (!StringUtils.isEmpty(permission)) {
        pSet.add(permission);
      }
    }

    //通过配置文件查找白名单的资源;
    /*Integer id = bmd.getUserid();
    if (id.equals(user.getId())) {
      List<Integer> R = bmd.getRoles();
      List<String> bmdpermissions = rolesMapper.findPermissions(R.toArray(array));

      for (String permission : bmdpermissions) {
        if (!StringUtils.isEmpty(permission)) {
          pSet.add(permission);
        }
      }
    }
*/
    //给资源进行授权
    SimpleAuthorizationInfo info =
        new SimpleAuthorizationInfo();
    info.setStringPermissions(pSet);
    // authorizationCache.put(user.getUsername(),info);
    return info;

  }

  //退出操作,清除信息
  public void logout() {
    SecurityUtils.getSubject().logout();
  }
}

package com.zucc.front.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.zucc.front.bean.User;
import com.zucc.front.fegin.UserFeign;

import lombok.extern.slf4j.Slf4j;

/**
 * 自定义realm
 * @author luojiacheng
 *
 */
@Slf4j
public class UserRealm extends AuthorizingRealm{

  @Autowired
  private UserFeign userFeign;
  
  /**
   * 执行授权
   */
  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    
    return null;
  }

  /**
   * 认证逻辑
   */
  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
      throws AuthenticationException {
    log.info("开始进行登录逻辑认证");
    UsernamePasswordToken infoToken = (UsernamePasswordToken) token;
    User user = userFeign.checkPassword(infoToken.getUsername());
    if(user == null) {
      // 该用户不存在
      return null;
    }
    return new SimpleAuthenticationInfo("",user.getPassword(),"");
  }

}

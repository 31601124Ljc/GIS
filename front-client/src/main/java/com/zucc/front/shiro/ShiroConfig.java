package com.zucc.front.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * shiro配置
 * 
 * @author luojiacheng
 *
 */
@Configuration
public class ShiroConfig {

  /**
   * 创建shiroFilterFactoryBean
   * 
   * @param securityManager
   * @return
   */
  @Bean(name = "shiroFilterFactoryBean")
  public ShiroFilterFactoryBean getShiroFilterFactoryBean(
      @Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
    ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

    // 设置安全管理器
    shiroFilterFactoryBean.setSecurityManager(securityManager);

    // 内置过滤 anon:无需登录， authc:需登录认证， user:使用rememberMe可直接访问, perms:该资源需得到资源权限, role:该资源必须得到角色权限
    Map<String, String> filterMap = new LinkedHashMap<String, String>();
    filterMap.put("/login", "anon");
    filterMap.put("/account/login", "anon");
    filterMap.put("/bootstrap-3.3.7-dist/**", "anon");
    filterMap.put("/js/**", "anon");

    filterMap.put("/logout", "logout");
    filterMap.put("/**", "authc");
    
    // 拦截后跳转至登录路径
    shiroFilterFactoryBean.setLoginUrl("/login");
    // 登录成功后跳转
    shiroFilterFactoryBean.setSuccessUrl("/index");
    shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

    return shiroFilterFactoryBean;
  }

  /**
   * 创建SecurityManager
   * 
   * @param userRealm
   * @return
   */
  @Bean(name = "securityManager")
  public DefaultWebSecurityManager getDefaultWebSecurityManager(
      @Qualifier("userRealm") UserRealm userRealm) {
    DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
    securityManager.setRealm(userRealm);
    return securityManager;
  }


  /**
   * 创建realm
   * 
   * @return
   */
  @Bean(name = "userRealm")
  public UserRealm getRealm() {
    return new UserRealm();
  }
}

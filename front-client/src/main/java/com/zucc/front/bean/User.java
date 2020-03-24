package com.zucc.front.bean;

import java.util.Date;

import lombok.Data;

/**
 * 系统用户表
 * @author luojiacheng
 *
 */
@Data
public class User {

  private Integer id;
  
  private String username;
  
  private String password;
  
  private String userType;
  
  private String name;
  
  private Integer age;
  
  private String createUser;
  
  private Date createTime;
  
  private String updateUser;
  
  private Date updateTime;
  
  private Integer flag;
}

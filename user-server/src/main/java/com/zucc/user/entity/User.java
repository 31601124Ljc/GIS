package com.zucc.user.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * 系统用户表
 * @author luojiacheng
 *
 */
@Data
@Table(name = "system_user")
public class User {

  @Id
  @Column(name = "id")
  private Integer id;
  
  @Column(name = "username")
  private String username;
  
  @Column(name = "password")
  private String password;
  
  @Column(name = "userType")
  private String userType;
  
  @Column(name = "name")
  private String name;
  
  @Column(name = "age")
  private Integer age;
  
  @Column(name = "createUser")
  private String createUser;
  
  @Column(name = "createTime")
  private Date createTime;
  
  @Column(name = "updateUser")
  private String updateUser;
  
  @Column(name = "updateTime")
  private Date updateTime;
  
  @Column(name = "flag")
  private Integer flag;
}

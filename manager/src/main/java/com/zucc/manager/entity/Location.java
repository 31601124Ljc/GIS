package com.zucc.manager.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * 电子地图
 * @author luojiacheng
 *
 */
@Data
@Table(name = "location")
public class Location {

  @Id
  @Column(name = "id")
  private Integer id;
  
  @Column(name = "xAxis")
  private Double xAxis;
  
  @Column(name = "yAxis")
  private Double yAxis;
  
  @Column(name = "name")
  private String name;
  
  @Column(name = "location")
  private String location;
  
  @Column(name = "status")
  private String status;
  
  @Column(name = "updateUser")
  private String updateUser;
  
  @Column(name = "updateTime")
  private Date updateTime;
}

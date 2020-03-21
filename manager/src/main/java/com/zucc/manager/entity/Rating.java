package com.zucc.manager.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;

import lombok.Data;

/**
 * 大众评分表
 * @author luojiacheng
 *
 */
@Data
@Table(name = "ratings")
public class Rating {

  @Column(name = "id")
  private Integer id;
  
  @Column(name = "userId")
  private Integer userId;
  
  @Column(name = "missionId")
  private Integer missionId;
  
  @Column(name = "ratings")
  private Double ratings;
  
  @Column(name = "reason")
  private String reason;
  
  @Column(name = "updateTime")
  private Date updateTime;
}

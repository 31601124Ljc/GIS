package com.zucc.manager.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * 任务记录表
 * @author luojiacheng
 *
 */
@Data
@Table(name = "missions")
public class Mission {

  @Id
  @Column(name = "id")
  private Integer id;
  
  // 发布任务用户id
  @Column(name = "managerUserId")
  private Integer managerUserId;
  
  // 施工用户id
  @Column(name = "workerUserId")
  private Integer workerUserId;
  
  // 任务地点id
  @Column(name = "workPlaceId")
  private Integer workPlaceId;
  
  // 工作类别（修缮、维护、扩建）
  @Column(name = "workContent")
  private String workContent;
  
  // 任务详情描述
  @Column(name = "details")
  private String details;
  
  // 任务进程
  @Column(name = "progress")
  private String progress;
  
  // 任务完成情况
  @Column(name = "completion")
  private String completion;
  
  // 大众评分
  @Column(name = "popularRatings")
  private Double popularRatings;
  
  // 参与评分人数
  @Column(name = "ratingsNumber")
  private Integer ratingsNumber;
  
  // 任务开始时间
  @Column(name = "missionStartTime")
  private Date missionStartTime;
  
  // 任务结束时间
  @Column(name = "missionEndTime")
  private Date missionEndTime;
  
  // 更新时间
  @Column(name = "missionUpdateTime")
  private Date missionUpdateTime;
}

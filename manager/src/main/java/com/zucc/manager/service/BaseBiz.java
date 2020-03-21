package com.zucc.manager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


import lombok.extern.slf4j.Slf4j;
import tk.mybatis.mapper.common.Mapper;

@Slf4j
public abstract class BaseBiz<M extends Mapper<T>, T> {
  @Autowired
  protected M mapper;

  public void setMapper(M mapper) {
    this.mapper = mapper;
  }

  public T selectOne(T entity) {
    return mapper.selectOne(entity);
  }


  public T selectById(Object id) {
    return mapper.selectByPrimaryKey(id);
  }


  public List<T> selectList(T entity) {
    return mapper.select(entity);
  }


  public List<T> selectListAll() {
    return mapper.selectAll();
  }


  public Long selectCount(T entity) {
    return new Long(mapper.selectCount(entity));
  }
}


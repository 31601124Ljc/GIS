package com.zucc.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zucc.user.entity.User;

import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<User> {
  // 根据用户名查找该用户
  public List<User> findUserByUserName(@Param("username") String username, @Param("flag") Integer flag);
}

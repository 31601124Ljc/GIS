package com.zucc.user.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.zucc.user.entity.User;
import com.zucc.user.mapper.UserMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService extends BaseBiz<UserMapper, User> {
  
  // 用于校验用户名密码：根据用户名返回该用户信息
  public User checkPassword(String username) {
    log.info("进行密码校验");
    List<User> users = mapper.findUserByUserName(username, 0);
    if(users.size() == 0) {
      return null;
    }else {
      return users.get(0);
    }
  }
  
  // 更改密码(自己)
  public void changePasswordBySelf(User user, String newPassword) {
    User template = user;
    template.setUpdateTime(new Date());
    template.setUpdateUser(user.getUsername());
    template.setPassword(newPassword);
    mapper.updateByPrimaryKey(template);
  }
  
  // 管理员重置用户密码
  public void resetPassword(User admin, Integer id, String newPassword) {
    User user = new User();
    user.setId(id);
    user.setUpdateTime(new Date());
    user.setUpdateUser(admin.getUsername());
    user.setPassword(newPassword);
    mapper.updateByPrimaryKeySelective(user);
  }
  
  // 新用户注册
  public int addUser(User admin, User user, String userType) {
    Date now = new Date();
    user.setCreateTime(now);
    user.setCreateUser(admin.getUsername());
    user.setUpdateTime(now);
    user.setUpdateUser(admin.getUsername());
    user.setFlag(0);
    user.setUserType(userType);
    return mapper.insertSelective(user);
  }
  
  // 查找指定用户
  public List<User> findUsers(String username, Integer flag){
    return mapper.findUserByUserName(username, flag);
  }
}

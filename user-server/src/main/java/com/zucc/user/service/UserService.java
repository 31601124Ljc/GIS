package com.zucc.user.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.zucc.user.entity.User;
import com.zucc.user.mapper.UserMapper;

@Service
public class UserService extends BaseBiz<UserMapper, User> {
  
  // 校验用户名密码
  public String checkPassword(String username, String password) {
    List<User> users = mapper.findUserByUserName(username, 0);
    if(users.size() == 0) {
      return "该用户不存在";
    }
    else if(StringUtils.equals(password, users.get(0).getPassword())) {
      return "密码校验通过";
    }
    return "用户名或密码错误";
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

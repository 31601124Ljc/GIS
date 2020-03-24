package com.zucc.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zucc.user.entity.User;
import com.zucc.user.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService;
  
  @RequestMapping("/checkPassword")
  @ResponseBody
  public User checkPassword(@RequestParam("username") String username) throws Exception {
    return userService.checkPassword(username);
  }
}

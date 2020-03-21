package com.zucc.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

  @RequestMapping("/checkPassword")
  @ResponseBody
  public void login(String username, String password) {
    
  }
}

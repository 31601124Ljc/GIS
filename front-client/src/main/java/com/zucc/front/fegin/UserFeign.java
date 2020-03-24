package com.zucc.front.fegin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zucc.front.bean.User;

@FeignClient(value = "user-server")
@RequestMapping(value = "user-server")
public interface UserFeign {

  @RequestMapping("/user/checkPassword")
  public User checkPassword(@RequestParam("username") String username);
}

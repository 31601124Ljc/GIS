package com.zucc.front.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/")
public class WebController {

  @RequestMapping(value = "login")
  public String login() {
    log.info("访问登录页面");
    return "login.html";
  }
  
  @RequestMapping(value = "index")
  public String toIndex() {
    log.info("访问主页面");
    return "index.html";
  }
}

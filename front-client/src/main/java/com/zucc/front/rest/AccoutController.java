package com.zucc.front.rest;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.zucc.front.bean.User;
import com.zucc.front.fegin.UserFeign;
import com.zucc.front.model.RestApiResult;
import com.zucc.front.utils.JWTUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/account")
public class AccoutController {

  @Autowired
  private UserFeign userFeign;

  @RequestMapping(value = "/login")
  private RestApiResult<String> login(@RequestParam(required = true) String username,
      @RequestParam(required = true) String password, HttpSession session) {
    log.info("开始进行登录操作");
    RestApiResult<String> restApiResult = new RestApiResult<>();
    restApiResult.setRespCode(1);
    restApiResult.setRespMsg("登录成功");

    Subject subject = SecurityUtils.getSubject();
    UsernamePasswordToken token = new UsernamePasswordToken(username, password);

    try {
      subject.login(token);
      User account = userFeign.checkPassword(username);
      JSONObject subjectToken = new JSONObject(true);
      subjectToken.put("tid", account.getId());
      // token此处定义12小时有效，据实际应用场景确定有效性，也可以定义刷新机制，保持用户token的使用时限
      String accessToken = JWTUtils.createJWT(subjectToken.toJSONString());
      restApiResult.setRespData(accessToken);
    } catch (UnknownAccountException e) {
      // 用户名不存在
      log.error("用户名不存在");
      restApiResult = new RestApiResult<>("用戶名不存在");
    } catch (IncorrectCredentialsException e) {
      // 用户名或密码错误
      log.error("用户名或密码错误,登陆失败");
      restApiResult = new RestApiResult<>("用户名或密码错误，登陆失败");
    } catch (Exception e) {
      log.error("生成jwt异常{}", e);
    }
    log.info("执行完成");
    return restApiResult;
  }
}

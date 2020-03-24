package com.zucc.front.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.zucc.front.config.SystemConstants;

import lombok.Data;

// @ApiModel(value = "result")
@Data
public class RestApiResult<T> implements Serializable {

  private static final long serialVersionUID = 1L;

  // @ApiModelProperty(value = "respCode : 返回代码，1表示成功，其它的都有对应问题")
  private int respCode = 1;

  // @ApiModelProperty(value = "respMsg : 如果code!=1,错误信息")
  private String respMsg = "成功！";

  @SuppressWarnings("unchecked")
  // @ApiModelProperty(value = "respCode为1时返回结果")
  private T respData = (T) new Object();

  // @ApiModelProperty(value = "附加信息")
  private Map<String, Object> respMap = new HashMap<String, Object>();

  // @ApiModelProperty(value = "接口返回数据的签名信息")
  private String signature;

  // @ApiModelProperty(value = "请求接口时的设备类型")
  private String deviceType;

  // @ApiModelProperty(value = "respData base64加密，用于客户端验证签名")
  private String dataCode;

  public RestApiResult(String errorMsg) {
    this.respMsg = errorMsg;
    this.respCode = SystemConstants.Code.error;
    this.respData = (T) new Object();
    this.respMap = new HashMap<String, Object>();
  }

  public RestApiResult() {
    this.respData = (T) new Object();
    this.respMap = new HashMap<String, Object>();
  }

  public void success(T object) {
    this.respCode = SystemConstants.Code.success;
    this.respMsg = SystemConstants.Code.SUCCESS;
    this.respData = object;
    this.respMap = new HashMap<String, Object>();
  }

  public void error() {
    this.respCode = SystemConstants.Code.error;
    this.respMsg = SystemConstants.Code.FAIL;
    this.respData = (T) new Object();
    this.respMap = new HashMap<String, Object>();
  }

  public void error(String message) {
    this.respCode = SystemConstants.Code.error;
    this.respMsg = message;
    this.respData = (T) new Object();
    this.respMap = new HashMap<String, Object>();
  }
}

package com.bootcamp.bc_stock_web.infra;

import lombok.Getter;

@Getter
public enum SysCode {
  OK(0, "success."), //
  USER_ID_NOT_FOUND(1, "User ID not found."), //
  INVALID_INPUT(2, "Invalid Input"), //
  RESTTEMPLATE_ERROR(3, "RestTemplate Error - JsonPlaceHolder"), //
  NOT_FOUND(11, "not found."), //
  LIST_ADD_FAIL(13, "List add fail"), //
  ;

  private int code;
  private String desc;

  private SysCode(int code, String desc) {
    this.code = code;
    this.desc = desc;
  }
}
package com.bootcamp.bc_stock_web.infra;

import lombok.Getter;

@Getter
public class BusinessRuntimeException extends RuntimeException {

  private int code;

  public BusinessRuntimeException(SysCode sysCode) {
    super(sysCode.getDesc());
    this.code = sysCode.getCode();
  }

}
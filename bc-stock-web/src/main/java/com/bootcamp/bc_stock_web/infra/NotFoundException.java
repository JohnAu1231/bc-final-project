package com.bootcamp.bc_stock_web.infra;

public class NotFoundException extends BusinessRuntimeException {
  
  public NotFoundException() {
    super(SysCode.USER_ID_NOT_FOUND);
  }

}
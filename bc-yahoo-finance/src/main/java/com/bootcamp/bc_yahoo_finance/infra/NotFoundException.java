package com.bootcamp.bc_yahoo_finance.infra;

public class NotFoundException extends BusinessRuntimeException {
  
  public NotFoundException() {
    super(SysCode.USER_ID_NOT_FOUND);
  }

}
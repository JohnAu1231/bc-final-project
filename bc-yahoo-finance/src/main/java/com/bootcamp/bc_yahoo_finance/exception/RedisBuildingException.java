package com.bootcamp.bc_yahoo_finance.exception;

public class RedisBuildingException extends RuntimeException{
  
  public RedisBuildingException(String msg) {
   super(msg);
  }

  public RedisBuildingException(String msg, Throwable cause) {
    super(msg, cause);
   }
}

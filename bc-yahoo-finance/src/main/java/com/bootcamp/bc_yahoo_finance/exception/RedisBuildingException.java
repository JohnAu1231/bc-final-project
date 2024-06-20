package com.bootcamp.bc_yahoo_finance.exception;

import com.fasterxml.jackson.core.JsonProcessingException;

public class RedisBuildingException extends JsonProcessingException{
  
  public RedisBuildingException(String msg) {
   super(msg);
  }
}

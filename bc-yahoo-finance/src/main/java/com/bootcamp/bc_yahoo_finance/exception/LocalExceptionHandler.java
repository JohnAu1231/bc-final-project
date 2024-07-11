package com.bootcamp.bc_yahoo_finance.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.bootcamp.bc_yahoo_finance.infra.GlobalExceptionHandler;

@RestControllerAdvice
public class LocalExceptionHandler extends GlobalExceptionHandler {
  
}

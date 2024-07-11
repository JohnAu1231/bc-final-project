package com.bootcamp.bc_stock_web.infra;


import java.io.FileNotFoundException;
import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


public class GlobalExceptionHandler {
  

  @ExceptionHandler(BusinessRuntimeException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ApiResp<Void> busineesRuntimeExceptionHandler(BusinessRuntimeException e) {
    return ApiResp.<Void>builder() //
                  .code(e.getCode()) //
                  .message(e.getMessage()) //
                  .build();
  }
  
  @ExceptionHandler(NullPointerException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
 public ApiResp<Void> nullPointExceptionHandler(NullPointerException e) {
   return ApiResp.<Void>builder() //
                 .error(ErrorCode.NPE) //
                 .build();
 }

 @ExceptionHandler(NumberFormatException.class)
 @ResponseStatus(value = HttpStatus.BAD_REQUEST)
 public ApiResp<Void> numberFormatExceptionHandler(NumberFormatException e) {
   return ApiResp.<Void>builder() //
                 .error(ErrorCode.NFE) //
                 .build();
 }

  @ExceptionHandler(ArithmeticException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ApiResp<Void> arithmeticExceptionHandler(ArithmeticException e) {
    return ApiResp.<Void>builder() //
                  .error(ErrorCode.AE) //
                  .build();
  }

  @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ApiResp<Void> arrayIndexOutOfBoundsExceptionHandler(ArrayIndexOutOfBoundsException e) {
    return ApiResp.<Void>builder() //
                  .error(ErrorCode.AIOBE) //
                  .build();
  }

  @ExceptionHandler(ClassNotFoundException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ApiResp<Void> classNotFoundExceptionHandler(ClassNotFoundException e) {
    return ApiResp.<Void>builder() //
                  .error(ErrorCode.CNFE) //
                  .build();
  }

  @ExceptionHandler(FileNotFoundException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ApiResp<Void> fileNotFoundExceptionHandler(FileNotFoundException e) {
    return ApiResp.<Void>builder() //
                  .error(ErrorCode.FNFE) //
                  .build();
  }

  @ExceptionHandler(IOException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ApiResp<Void> IOExceptionExceptionHandler(IOException e) {
    return ApiResp.<Void>builder() //
                  .error(ErrorCode.IOE) //
                  .build();
  }

  @ExceptionHandler(StringIndexOutOfBoundsException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ApiResp<Void> stringIndexOutOfBoundsExceptionHandler(StringIndexOutOfBoundsException e) {
    return ApiResp.<Void>builder() //
                  .error(ErrorCode.SIOBE) //
                  .build();
  }

  @ExceptionHandler(IllegalArgumentException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ApiResp<Void> illegalArgumentExceptionHandler(IllegalArgumentException e) {
    return ApiResp.<Void>builder() //
                  .error(ErrorCode.IAE) //
                  .build();
  }

  @ExceptionHandler(IllegalStateException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ApiResp<Void> illegalStateExceptionHandler(IllegalStateException e) {
    return ApiResp.<Void>builder() //
                  .error(ErrorCode.ISE) //
                  .build();
  }




  @ExceptionHandler(Exception.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ApiResp<Void> unhandledException() {
    return ApiResp.<Void>builder() //
              .error(ErrorCode.UNKNOWN_ERROR)
              .build();
  }

}
package com.bootcamp.bc_yahoo_finance.infra;


import java.io.FileNotFoundException;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import com.bootcamp.bc_yahoo_finance.exception.RedisBuildingException;
import com.bootcamp.bc_yahoo_finance.exception.StockDataNotAvailableException;
import com.fasterxml.jackson.core.JsonProcessingException;

// @ContollerAdvice + @ResponseBody
public class GlobalExceptionHandler {

  private static final Logger logger =
      LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler(JsonProcessingException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<String> handleJsonProcessingException(
      JsonProcessingException ex, WebRequest request) {
    logger.error("JSON processing error", ex);
    return new ResponseEntity<>("JSON processing error: " + ex.getMessage(),
        HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(RedisBuildingException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseEntity<String> handleCustomJsonProcessingException(
      RedisBuildingException ex, WebRequest request) {
    logger.error("Custom JSON processing error", ex);
    return new ResponseEntity<>(
        "Custom JSON processing error: " + ex.getMessage(),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(BusinessRuntimeException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ApiResp<Void> busineesRuntimeExceptionHandler(
      BusinessRuntimeException e) {
    return ApiResp.<Void>builder() //
        .code(e.getCode()) //
        .message(e.getMessage()) //
        .build();
  }

  @ExceptionHandler(NullPointerException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ApiResp<Void> nullPointExceptionHandler(NullPointerException e) {
    return ApiResp.<Void>builder() //
        .code(ErrorCode.NPE.getCode()) //
        .message(ErrorCode.NPE.getDesc() + " : " + e.getMessage())
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
  public ApiResp<Void> arrayIndexOutOfBoundsExceptionHandler(
      ArrayIndexOutOfBoundsException e) {
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
  public ApiResp<Void> stringIndexOutOfBoundsExceptionHandler(
      StringIndexOutOfBoundsException e) {
    return ApiResp.<Void>builder() //
        .error(ErrorCode.SIOBE) //
        .build();
  }

  @ExceptionHandler(IllegalArgumentException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ApiResp<Void> illegalArgumentExceptionHandler(
      IllegalArgumentException e) {
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

  @ExceptionHandler(StockDataNotAvailableException.class)
  public ApiResp<Void> handleStockDataNotAvailableException(StockDataNotAvailableException e) {
  return ApiResp.<Void>builder()
  .code(501)
  .message("Resource not found: " + e.getMessage())
  .build();
  }

  @ExceptionHandler(NoHandlerFoundException.class)
  public ApiResp<Void> handleNoHandlerFoundException(NoHandlerFoundException e) {
  return ApiResp.<Void>builder()
  .code(404)
  .message("Resource not found: " + e.getRequestURL())
  .build();
  }
  
  @ExceptionHandler(NoResourceFoundException.class)
  public ApiResp<Void> handleNoResourceFoundException(NoResourceFoundException e) {
  return ApiResp.<Void>builder()
  .code(500)
  .message("Resource not found: " + e.getDetailMessageCode())
  .build();
  }

  @ExceptionHandler(Exception.class)
  public ApiResp<Void> unhandledException(Exception e) {
    
  return ApiResp.<Void>builder() //
  .code(ErrorCode.UNKNOWN_ERROR.getCode())
  .message(e.getMessage())
  .build();
  }

  // @ExceptionHandler(Exception.class)
  // public ApiResp<Void> unhandledException(Exception ex, WebRequest request) {
  //   // logger.info("responser:" + response.getStatus());
  //   ResponseEntity<String> response = ResponseEntity.of(Optional.of(request.toString()));
  //  if( response.getStatusCode().is4xxClientError()){
  //   return ApiResp.<Void>builder()//
  //   .code(0)//
  //   .build();
  //  }else if( response.getStatusCode().is5xxServerError()){

  //  }

  //  if(e instanceof NoResourceFoundException )
  //   if (response.getStatus() > 400 && response.getStatus() <= 500) {
  //     // if (response.getStatus() == HttpStatus.NOT_FOUND.value()) {
  //     return ApiResp.<Void>builder() //
  //         // .code(500)
  //         .message("internal server error").build();
  //   } else {
  //     return ApiResp.<Void>builder() //
  //         .error(ErrorCode.UNKNOWN_ERROR).build();
  //   }
  // }
}

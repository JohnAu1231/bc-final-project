package com.bootcamp.bc_yahoo_finance.infra;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import com.bootcamp.bc_yahoo_finance.exception.RedisBuildingException;
import com.fasterxml.jackson.core.JsonProcessingException;

// @ContollerAdvice + @ResponseBody
public class GlobalExceptionHandler {

  private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(JsonProcessingException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleJsonProcessingException(JsonProcessingException ex, WebRequest request) {
        logger.error("JSON processing error", ex);
        return new ResponseEntity<>("JSON processing error: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RedisBuildingException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleCustomJsonProcessingException(RedisBuildingException ex, WebRequest request) {
        logger.error("Custom JSON processing error", ex);
        return new ResponseEntity<>("Custom JSON processing error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


  
 
   @ExceptionHandler(ArithmeticException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ApiResp<Void> arithmeticExceptionExceptionHandler(ArithmeticException e) {
    return ApiResp.<Void>builder() //
                  .error(ErrorCode.AE) //
                  .build();
   
  }

  @ExceptionHandler(NumberFormatException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ApiResp<Void> numberFormatExceptionHandler(NumberFormatException e) {
    return ApiResp.<Void>builder() //
                  .error(ErrorCode.NFE) //
                  .build();
  }

   @ExceptionHandler(NullPointerException.class)
   @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ApiResp<Void> nullPointExceptionHandler(NullPointerException e) {
    return ApiResp.<Void>builder() //
                  .error(ErrorCode.NPE) //
                  .build();
  }

  


  @ExceptionHandler(BusinessRuntimeException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ApiResp<Void> busineesRuntimeExceptionHandler(BusinessRuntimeException e) {
    return ApiResp.<Void>builder() //
                  .code(e.getCode()) //
                  .message(e.getMessage()) //
                  .build();
  }


  // @ExceptionHandler(Exception.class)
  // @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  // public ApiResp<Void> unhandledException() {
  //   return ApiResp.<Void>builder() //
  //             .code(9999) //
  //             .message("Unhandled exception.") //
  //             .build();
  // }

      // Handle other exceptions
      @ExceptionHandler(Exception.class)
      @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
      public ResponseEntity<String> handleGlobalException(Exception ex, WebRequest request) {
          logger.error("An error occurred", ex);
          return new ResponseEntity<>("An error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      }

  // HttpMessageNotReadableException
 }
package com.bootcamp.bc_stock_web.infra;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class ApiResp<T> {

  private int code;
  private String message;
  private List<T> data;

  public ApiResp(ApiRespBuilder<T> apiRespBuilder) {
    this.code = apiRespBuilder.code;
    this.message = apiRespBuilder.message;
    this.data = apiRespBuilder.data;
  }

  public static <T> ApiRespBuilder<T> builder() {
    return new ApiRespBuilder<>();
  }

  public int getCode() {
    return this.code;
  }

  public String getMessage() {
    return this.message;
  }

  public List<T> getData() {
    return this.data;
  }

  public static class ApiRespBuilder<T> {
    private int code;
    private String message;
    private List<T> data;

    public ApiRespBuilder<T> code(int code) {
      this.code = code;
      return this;
    }

    public ApiRespBuilder<T> message(String message) {
      if(message == null) 
        throw new NullPointerException("message should not be null.");
      this.message = message;
      return this;
    }

    public ApiRespBuilder<T> data(List<T> data) {
      this.data = data;
      return this;
    }

    public ApiRespBuilder<T> error(ErrorCode errorCode) {
      this.code = errorCode.getCode();
      this.message = errorCode.getDesc();
      return this;
    }

    public ApiRespBuilder<T> error(SysCode sysCode) {
      this.code = sysCode.getCode();
      this.message = sysCode.getDesc();
      return this;
    }

    public ApiResp<T> build() {
      return new ApiResp<T>(this);
    }

    public ApiRespBuilder<T> ok() {
      this.code = 0;
      this.message = "Success.";
      return this;
    }

  }

  public String toString() {
    return "ApiResp(" //
        + "code=" + this.getCode() //
        + ", message=" + this.getMessage() //
        + ", data=" + String.valueOf(this.getData()) //
        + ")";
  }

}
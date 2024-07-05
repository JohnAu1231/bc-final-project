package com.bootcamp.bc_yahoo_finance.exception;

public class StockDataNotAvailableException extends RuntimeException {
  public StockDataNotAvailableException(String message) {
      super(message);
  }
}
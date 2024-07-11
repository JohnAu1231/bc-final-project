package com.bootcamp.bc_yahoo_finance.infra;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode{
  API_ERROR(50, "API error"),
  NPE(99, "Null Pointer Exception."), //
  NFE(98, "NumberFormat Exception."), //
  AE(97, "Arithmetic Exception."), //
  AIOBE(96, "ArrayIndexOutOfBoundsException."), //
  CNFE(95, "ClassNotFoundException."), //
  FNFE(94, "FileNotFoundException."), //
  IOE(93, "IOException."), //
  SIOBE(92, "StringIndexOutOfBoundsExceptio."), //
  IAE(91, "IllegalArgumentException."), //
  ISE(90, "IllegalStateException.") , //
  UNKNOWN_ERROR(999, "Unknown error"),
;
private int code;
private String desc;
}

package com.bootcamp.bc_yahoo_finance.infra;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode{
NPE(99, "Null Pointer Exception."), //
NFE(99, "NumberFormat Exception."), //
AE(99, "Arithmetic Exception."), //
AIOBE(99, "ArrayIndexOutOfBoundsException."), //
CNFE(99, "ClassNotFoundException."), //
FNFE(99, "FileNotFoundException."), //
IOE(99, "IOException."), //
SIOBE(99, "StringIndexOutOfBoundsExceptio."), //
IAE(99, "IllegalArgumentException."), //
ISE(99, "IllegalStateException.") , //
;
private int code;
private String desc;
}

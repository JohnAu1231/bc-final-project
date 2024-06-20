package com.bootcamp.bc_yahoo_finance.infra;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

public class LocalDateTimeConverter {
  
  public static LocalDateTime of(Timestamp timestamp) {
    return LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp.getTime()), TimeZone.getDefault().toZoneId());
 }
}

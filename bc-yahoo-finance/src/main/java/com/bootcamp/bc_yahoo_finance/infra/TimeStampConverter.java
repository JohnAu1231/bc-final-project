package com.bootcamp.bc_yahoo_finance.infra;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;
import org.springframework.cglib.core.Local;

public class TimeStampConverter {
  


  public static Timestamp of(LocalDateTime localDateTime) {
    return Timestamp.valueOf(localDateTime);
  }
}

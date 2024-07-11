package com.bootcamp.bc_stock_web.infra;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class TimeStampConverter {
  


  public static Timestamp of(LocalDateTime localDateTime) {
    return Timestamp.valueOf(localDateTime);
  }
}

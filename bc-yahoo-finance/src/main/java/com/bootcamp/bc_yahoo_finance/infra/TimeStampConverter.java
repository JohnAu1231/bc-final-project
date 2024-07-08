package com.bootcamp.bc_yahoo_finance.infra;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.WeekFields;

public class TimeStampConverter {
  


  public static Timestamp of(LocalDateTime localDateTime) {
    return Timestamp.valueOf(localDateTime);
  }

  public static int toWeekOfYear(Timestamp timestamp) {
    LocalDate localDate = timestamp.toLocalDateTime().toLocalDate();
    int weekOfYear = localDate.get(WeekFields.ISO.weekOfWeekBasedYear());
    return weekOfYear;
  }
        
}

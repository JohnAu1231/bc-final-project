package com.bootcamp.bc_yahoo_finance.infra;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.TimeZone;

public class LocalDateTimeConverter {
  
  public static LocalDateTime of(Timestamp timestamp) {
    return LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp.getTime()), TimeZone.getDefault().toZoneId());
 }

   public static LocalDateTime of(Long number) {
    Timestamp timestamp = new Timestamp(Long.valueOf(number) * 1000L);
    LocalDateTime time = timestamp.toInstant().atZone(ZoneId.of("Asia/Hong_Kong"))
      .toLocalDateTime();
      return time;
  }

  public static long toLong(LocalDateTime localDateTime) {
    return Timestamp.valueOf(localDateTime).getTime();
  }

  // public static LocalDateTime ofDayStart(LocalDateTime localDateTime) {
  //   // LocalDate localDate = LocalDate.parse(null)
  //   // LocalDateTime startOfDay = Local
  // }
}

package com.bootcamp.bc_yahoo_finance.infra;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
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

  // input a time to get timestamp for start of day and end of day
  public static Timestamp startOf(Timestamp time) {
    ZoneId zoneId = ZoneId.of("Asia/Hong_Kong");
    LocalDateTime nowDateTime =
        Instant.ofEpochMilli(time.getTime()).atZone(zoneId).toLocalDateTime();
    LocalDate date = nowDateTime.toLocalDate();
    LocalDateTime startOfDay = date.atStartOfDay();
    ZonedDateTime startOfDayZoned = startOfDay.atZone(zoneId);
    return Timestamp.from(startOfDayZoned.toInstant());
  }

  public static Timestamp endOf(Timestamp time) {
    ZoneId zoneId = ZoneId.of("Asia/Hong_Kong");
    LocalDateTime nowDateTime =
        Instant.ofEpochMilli(time.getTime()).atZone(zoneId).toLocalDateTime();
    LocalDate date = nowDateTime.toLocalDate();
    LocalDateTime endOfDay = date.atTime(23, 59, 59);
    ZonedDateTime endOfDayZoned = endOfDay.atZone(zoneId);
    return Timestamp.from(endOfDayZoned.toInstant());
  }

  public static Timestamp startOf(LocalDateTime time) {
    ZoneId zoneId = ZoneId.of("Asia/Hong_Kong");
    LocalDate date = time.toLocalDate();
    LocalDateTime startOfDay = date.atStartOfDay();
    ZonedDateTime startOfDayZoned = startOfDay.atZone(zoneId);
    return Timestamp.from(startOfDayZoned.toInstant());
  }

public static Timestamp endOf(LocalDateTime time) {
  ZoneId zoneId = ZoneId.of("Asia/Hong_Kong");
LocalDate date = time.toLocalDate();
LocalDateTime endOfDay = date.atTime(23, 59, 59);
ZonedDateTime endOfDayZoned = endOfDay.atZone(zoneId);
return Timestamp.from(endOfDayZoned.toInstant());
}

  public static Timestamp startOf(Long time) {
    ZoneId zoneId = ZoneId.of("Asia/Hong_Kong");
    ZonedDateTime zonedDateTime = Instant.ofEpochMilli(time).atZone(zoneId);
    LocalDate date = zonedDateTime.toLocalDate();
    LocalDateTime startOfDay = date.atStartOfDay();
    ZonedDateTime startOfDayZoned = startOfDay.atZone(zoneId);
    return Timestamp.from(startOfDayZoned.toInstant());
  }

  public static Timestamp endOf(Long time) {
    ZoneId zoneId = ZoneId.of("Asia/Hong_Kong");
    ZonedDateTime zonedDateTime = Instant.ofEpochMilli(time).atZone(zoneId);
    LocalDate date = zonedDateTime.toLocalDate();
    LocalDateTime endOfDay = date.atTime(23, 59, 59);
    ZonedDateTime endOfDayZoned = endOfDay.atZone(zoneId);
    return Timestamp.from(endOfDayZoned.toInstant());
  }


}

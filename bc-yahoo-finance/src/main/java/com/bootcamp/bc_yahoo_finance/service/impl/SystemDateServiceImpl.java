package com.bootcamp.bc_yahoo_finance.service.impl;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.TimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bootcamp.bc_yahoo_finance.entity.SystemDate;
import com.bootcamp.bc_yahoo_finance.infra.RedisHelper;
import com.bootcamp.bc_yahoo_finance.repository.YahooStockRepository;
import com.bootcamp.bc_yahoo_finance.service.SystemDateService;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class SystemDateServiceImpl implements SystemDateService {

  @Autowired
  private RedisHelper redisHelper;

  @Autowired
  private YahooStockRepository yahooStockRepository;
  
  @Override
  public String getSystemData(String symbol) {
    try {
      String name = "SYSDATE-".concat(symbol);
       String systemdate = redisHelper.get(name , String.class);
      if (systemdate != null) {
        return systemdate;
      } else {
        Long now = yahooStockRepository.findMaxMarketTimeBySymbol(symbol).orElse(null);
      
        if (now == null)
          return null;
        LocalDateTime time = LocalDateTime.ofInstant(Instant.ofEpochSecond(now), TimeZone.getDefault().toZoneId());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDateTime = time.format(formatter);
        redisHelper.set("SYSDATE-".concat(symbol), formattedDateTime, Duration.ofHours(4L));
        return formattedDateTime;
      }
    } catch(JsonProcessingException e) {
      return null;
    }
  }
}

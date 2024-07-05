package com.bootcamp.bc_yahoo_finance.mapper;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.bootcamp.bc_yahoo_finance.dto.reqDto.YahooStockDTO;
import com.bootcamp.bc_yahoo_finance.dto.respDto.ExYahooHistory;
import com.bootcamp.bc_yahoo_finance.entity.YahooStockEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class YahooStockEntityMapper {
  
  public YahooStockDTO mapToYahooStockDTO(YahooStockEntity yahooStockEntity) {
 
    if (yahooStockEntity == null)
      return null;
      // timestamp.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
      Timestamp timestamp = new Timestamp(yahooStockEntity.getRegularMarketUnix().longValue() * 1000L);
      
      LocalDateTime time = timestamp.toInstant().atZone(ZoneId.of("Asia/Hong_Kong"))
        .toLocalDateTime();
    YahooStockDTO yahooStockDTO = YahooStockDTO.builder().regularMarketPrice(yahooStockEntity.getMarketPrice())
    .marketTime(time).regularMarketUnix(yahooStockEntity.getRegularMarketUnix()).ask(yahooStockEntity.getAsk())
    .askSize(yahooStockEntity.getAskSize()).bid(yahooStockEntity.getBid())
    .bidSize(yahooStockEntity.getBidSize()).symbol(yahooStockEntity.getSymbol())
    .regularMarketChangePercent(yahooStockEntity.getRegularMarketChangePercent())
    .high(yahooStockEntity.getHigh())
    .low(yahooStockEntity.getLow())
    .open(yahooStockEntity.getOpen())
    .close(yahooStockEntity.getClose())
    .volume(yahooStockEntity.getVolume())
    .build();
    return yahooStockDTO;
  }

  

}

package com.bootcamp.bc_yahoo_finance.mapper;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import org.springframework.stereotype.Component;
import com.bootcamp.bc_yahoo_finance.dto.reqDto.YahooStockDTO;
import com.bootcamp.bc_yahoo_finance.entity.YahooStockEntity;

@Component
public class YahooStockEntityMapper {

  public YahooStockDTO mapToYahooStockDTO(YahooStockEntity yahooStockEntity) {

    if (yahooStockEntity == null)
      return null;

    Timestamp timestamp = new Timestamp(
        yahooStockEntity.getRegularMarketUnix().longValue() * 1000L);
    LocalDateTime time = timestamp.toInstant()
        .atZone(ZoneId.of("Asia/Hong_Kong")).toLocalDateTime();
    YahooStockDTO yahooStockDTO = YahooStockDTO.builder()
        .regularMarketPrice(yahooStockEntity.getMarketPrice()).marketTime(time)
        .regularMarketUnix(yahooStockEntity.getRegularMarketUnix())
        .ask(yahooStockEntity.getAsk()).askSize(yahooStockEntity.getAskSize())
        .bid(yahooStockEntity.getBid()).bidSize(yahooStockEntity.getBidSize())
        .symbol(yahooStockEntity.getSymbol())
        .regularMarketChangePercent(
            yahooStockEntity.getRegularMarketChangePercent())
        .high(yahooStockEntity.getHigh()).low(yahooStockEntity.getLow())
        .open(yahooStockEntity.getOpen()).close(yahooStockEntity.getClose())
        .volume(yahooStockEntity.getVolume()).build();
    return yahooStockDTO;
  }



}

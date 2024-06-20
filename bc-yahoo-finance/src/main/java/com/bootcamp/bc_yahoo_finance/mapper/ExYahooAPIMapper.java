package com.bootcamp.bc_yahoo_finance.mapper;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.TimeZone;
import org.springframework.stereotype.Component;
import com.bootcamp.bc_yahoo_finance.dto.reqDto.YahooStockDTO;
import com.bootcamp.bc_yahoo_finance.dto.respDto.ExYahooAPI;
import com.bootcamp.bc_yahoo_finance.dto.respDto.ExYahooAPI.QuoteResponse.ExYahooStock;
import com.bootcamp.bc_yahoo_finance.entity.YahooStockEntity;

@Component
public class ExYahooAPIMapper {
  public YahooStockEntity mapToYahooFinanceEntity(ExYahooAPI exYahooAPI) {
    if (exYahooAPI == null)
      return null;
    if (exYahooAPI.getQuoteResponse() == null)
      return null;
    List<ExYahooStock> ls = exYahooAPI.getQuoteResponse().getResult();
    if (ls == null) {
      return null;
    } else {
      ExYahooStock stock = ls.get(0);
      Long now = stock.getRegularMarketTime();
    
      YahooStockEntity yahooFinanceEntity =
          YahooStockEntity.builder().marketPrice(stock.getMarketPrice())
              .regularMarketUnix(stock.getRegularMarketTime())
              .ask(stock.getAsk())
              .askSize(stock.getAskSize()).bid(stock.getBid())
              .bidSize(stock.getBidSize()).symbol(stock.getSymbol())
              .regularMarketChangePercent(stock.getRegularMarketChangePercent())
              .build();
      return yahooFinanceEntity;
    }
  }

  public YahooStockDTO mapToYahooStockDTO(ExYahooAPI exYahooAPI) {
    if (exYahooAPI == null)
      return null;
    if (exYahooAPI.getQuoteResponse() == null)
      return null;
    List<ExYahooStock> ls = exYahooAPI.getQuoteResponse().getResult();
    if (ls == null) {
      return null;
    } else {
      ExYahooStock stock = ls.get(0);
      Timestamp timestamp =
          new Timestamp(stock.getRegularMarketTime().longValue() * 1000L);
      LocalDateTime time = timestamp.toInstant().atZone(ZoneId.systemDefault())
          .toLocalDateTime();
      YahooStockDTO yahooStockDTO = YahooStockDTO.builder()
          .marketPrice(stock.getMarketPrice()).marketTime(time)
          .ask(stock.getAsk()).askSize(stock.getAskSize()).bid(stock.getBid())
          .bidSize(stock.getBidSize()).symbol(stock.getSymbol())
          .regularMarketChangePercent(stock.getRegularMarketChangePercent())
          .build();
      return yahooStockDTO;
    }
  }
}

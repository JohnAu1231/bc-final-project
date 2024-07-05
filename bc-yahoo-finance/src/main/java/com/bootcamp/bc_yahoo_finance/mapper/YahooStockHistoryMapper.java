package com.bootcamp.bc_yahoo_finance.mapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.bootcamp.bc_yahoo_finance.dto.reqDto.FiveMinListDTO;
import com.bootcamp.bc_yahoo_finance.dto.reqDto.StockDayPriceDTO;
import com.bootcamp.bc_yahoo_finance.dto.reqDto.YahooStockDTO;
import com.bootcamp.bc_yahoo_finance.dto.respDto.ExYahooHistory;
import com.bootcamp.bc_yahoo_finance.entity.YahooStockEntity;
import com.bootcamp.bc_yahoo_finance.infra.LocalDateTimeConverter;
import com.bootcamp.bc_yahoo_finance.infra.TimeStampConverter;

@Component
public class YahooStockHistoryMapper {
  
  
  public FiveMinListDTO mapToStockDayPriceDTO(ExYahooHistory exYahooHistory) {

    if (exYahooHistory == null) {
      return null;
    }

    List<Long> volumes = new ArrayList<>();
    List<Double> opens = new ArrayList<>();
    List<Double> closes = new ArrayList<>();
    List<Double> highes = new ArrayList<>();
    List<Double> lowes = new ArrayList<>();
    List<Long> timestamp = new ArrayList<>();

    exYahooHistory.getChart().getResult().get(0).getTimestamp().stream().forEach(e -> timestamp.add(e));
    
    exYahooHistory.getChart().getResult().get(0).getIndicators().getQuote().stream().forEach(e -> {
      volumes.addAll(e.getVolume());
      opens.addAll(e.getOpen());
      closes.addAll(e.getClose());
      highes.addAll(e.getHigh());
      lowes.addAll(e.getLow());

    });
  
    List<YahooStockDTO> dtos = new ArrayList<>();
    for (int i = 0; i < timestamp.size(); i++) {
      dtos.add(YahooStockDTO.builder().marketTime( LocalDateTimeConverter.of(timestamp.get(i)))
      .regularMarketUnix(timestamp.get(i))
      .volume(volumes.get(i))
      .open(opens.get(i))
      .close(closes.get(i))
      .high(highes.get(i))
      .low(lowes.get(i))
      .build()
      );
    }

    return FiveMinListDTO.builder().regularMarketTIme(LocalDateTimeConverter.toLong(LocalDateTime.now())).data(dtos).build();
  }

  public FiveMinListDTO mapToStockWeekPriceDTO(ExYahooHistory exYahooHistory) {

    if (exYahooHistory == null) {
      return null;
    }

    List<Long> volumes = new ArrayList<>();
    List<Double> opens = new ArrayList<>();
    List<Double> closes = new ArrayList<>();
    List<Double> highes = new ArrayList<>();
    List<Double> lowes = new ArrayList<>();
    List<Long> timestamp = new ArrayList<>();

    exYahooHistory.getChart().getResult().get(0).getTimestamp().stream().forEach(e -> timestamp.add(e));
    
    exYahooHistory.getChart().getResult().get(0).getIndicators().getQuote().stream().forEach(e -> {
      volumes.addAll(e.getVolume());
      opens.addAll(e.getOpen());
      closes.addAll(e.getClose());
      highes.addAll(e.getHigh());
      lowes.addAll(e.getLow());

    });
  
    List<YahooStockDTO> dtos = new ArrayList<>();
    for (int i = 0; i < timestamp.size(); i++) {
      dtos.add(YahooStockDTO.builder().marketTime( LocalDateTimeConverter.of(timestamp.get(i)))
      .regularMarketUnix(timestamp.get(i))
      .volume(volumes.get(i))
      .open(opens.get(i))
      .close(closes.get(i))
      .high(highes.get(i))
      .low(lowes.get(i))
      .build()
      );
    }

    return FiveMinListDTO.builder().regularMarketTIme(LocalDateTimeConverter.toLong(LocalDateTime.now())).data(dtos).build();
  }

  
  public List<YahooStockEntity> mapToStockEntity(ExYahooHistory exYahooHistory) {

    if (exYahooHistory == null) {
      return null;
    }

    List<Long> volumes = new ArrayList<>();
    List<Double> opens = new ArrayList<>();
    List<Double> closes = new ArrayList<>();
    List<Double> highes = new ArrayList<>();
    List<Double> lowes = new ArrayList<>();
    List<Long> timestamp = new ArrayList<>();

    exYahooHistory.getChart().getResult().get(0).getTimestamp().stream().forEach(e -> timestamp.add(e));
    
    exYahooHistory.getChart().getResult().get(0).getIndicators().getQuote().stream().forEach(e -> {
      volumes.addAll(e.getVolume());
      opens.addAll(e.getOpen());
      closes.addAll(e.getClose());
      highes.addAll(e.getHigh());
      lowes.addAll(e.getLow());

    });
  
    List<YahooStockEntity> dtos = new ArrayList<>();
    for (int i = 0; i < timestamp.size(); i++) {
      dtos.add(YahooStockEntity.builder()
      .regularMarketUnix(timestamp.get(i))
      .symbol(exYahooHistory.getChart().getResult().get(0).getMeta().getSymbol())
      .volume(volumes.get(i))
      .open(opens.get(i))
      .close(closes.get(i))
      .high(highes.get(i))
      .low(lowes.get(i))
      .dataType("history")
      .build()
      );
    }

    return dtos;
  }

  
  public List<YahooStockEntity> mapToStockEntity(ExYahooHistory exYahooHistory, String interval) {

    if (exYahooHistory == null || exYahooHistory.getChart() == null || exYahooHistory.getChart().getResult().isEmpty()) {
      return null;
    }

    Long firstTrade = exYahooHistory.getChart().getResult().get(0).getMeta().getFirstTradeDate();
    if (firstTrade ==  0L)  {
      return null;
    }


    List<Long> volumes = new ArrayList<>();
    List<Double> opens = new ArrayList<>();
    List<Double> closes = new ArrayList<>();
    List<Double> highes = new ArrayList<>();
    List<Double> lowes = new ArrayList<>();
    List<Long> timestamp = new ArrayList<>();


    exYahooHistory.getChart().getResult().get(0).getTimestamp().stream().forEach(e -> timestamp.add(e));
    
    exYahooHistory.getChart().getResult().get(0).getIndicators().getQuote().stream().forEach(e -> {
      volumes.addAll(e.getVolume());
      opens.addAll(e.getOpen());
      closes.addAll(e.getClose());
      highes.addAll(e.getHigh());
      lowes.addAll(e.getLow());

    });
  
    List<YahooStockEntity> dtos = new ArrayList<>();
    for (int i = 0; i < timestamp.size(); i++) {
      dtos.add(YahooStockEntity.builder()
      .regularMarketUnix(timestamp.get(i))
      .symbol(exYahooHistory.getChart().getResult().get(0).getMeta().getSymbol())
      .volume(volumes.get(i))
      .open(opens.get(i))
      .close(closes.get(i))
      .high(highes.get(i))
      .low(lowes.get(i))
      .dataType("history".concat(interval))
      .build()
      );
    }

    return dtos;
  }
}

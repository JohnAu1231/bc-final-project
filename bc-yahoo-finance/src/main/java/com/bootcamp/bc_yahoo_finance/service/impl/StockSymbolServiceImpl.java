package com.bootcamp.bc_yahoo_finance.service.impl;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bootcamp.bc_yahoo_finance.dto.respDto.ExStock;
import com.bootcamp.bc_yahoo_finance.entity.StockRedisEntity;
import com.bootcamp.bc_yahoo_finance.entity.StockSymbolEntity;
import com.bootcamp.bc_yahoo_finance.infra.RedisHelper;
import com.bootcamp.bc_yahoo_finance.mapper.YahooSymbolMapper;
import com.bootcamp.bc_yahoo_finance.repository.StockSymbolRepository;
import com.bootcamp.bc_yahoo_finance.service.StockSymbolService;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class StockSymbolServiceImpl implements StockSymbolService {
  
  @Autowired
  private StockSymbolRepository stockSymbolRepository;

  @Autowired
  private YahooSymbolMapper yahooMapper;

  @Autowired
  private RedisHelper redisHelper;

  @Override
  public void saveStockSymbol() {
    List<ExStock> ls = new ArrayList<>(List.of(new ExStock("0388.HK"),new ExStock("0700.HK"))); 
    ls.stream() //
    .map(e -> yahooMapper.mapStocksEntity(e)) //
    .forEach(e -> stockSymbolRepository.save(e)); //
  }

  @Override
  public void deleteAll() {
    stockSymbolRepository.deleteAll();
  }

  @Override
  public StockRedisEntity getStockList() throws JsonProcessingException{
    StockSymbolEntity[] symbols = redisHelper.get("stock", StockSymbolEntity[].class);
    if (symbols != null) {
      List<StockSymbolEntity> stockSymbolEntities = stockSymbolRepository.findAll();
      StockRedisEntity stockRedisEntity = 
      yahooMapper.mapToStockRedisEntity(stockSymbolEntities);
      return stockRedisEntity;
    } else {
      List<StockSymbolEntity> stockSymbolEntities = stockSymbolRepository.findAll();
      StockRedisEntity stockRedisEntity = 
      yahooMapper.mapToStockRedisEntity(stockSymbolEntities);
      redisHelper.set("Stock-List", stockRedisEntity, Duration.ofDays(1L));
      return stockRedisEntity;
    }
  }



}

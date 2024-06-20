package com.bootcamp.bc_yahoo_finance.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bootcamp.bc_yahoo_finance.entity.StockRedisEntity;
import com.bootcamp.bc_yahoo_finance.service.StockSymbolService;
import com.bootcamp.bc_yahoo_finance.service.YahooEntityService;
import com.bootcamp.bc_yahoo_finance.service.YahooStockService;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class YahooEntityServiceImpl implements YahooEntityService{
 @Autowired
 private StockSymbolService stockSymbolService;
 @Autowired
 private YahooStockService yahooStockService;

    @Override
  public void saveYahooStockByStockList() throws JsonProcessingException {
    StockRedisEntity stockRedisEntity = stockSymbolService.getStockList();
    if (stockRedisEntity == null) 
        return;
    List<String> ls = stockRedisEntity.getSymbolList();
    if (ls == null)
        return;
    ls.stream().forEach(e -> yahooStockService.saveYahooAPI(e));
  }

  @Override
  public void saveStockFromYahoo() throws JsonProcessingException{
    StockRedisEntity stockList = stockSymbolService.getStockList();
    if (stockList == null)
      return;
  stockList.getSymbolList().stream().forEach(e -> yahooStockService.getYahooStockBySymbol(e));
  }
}

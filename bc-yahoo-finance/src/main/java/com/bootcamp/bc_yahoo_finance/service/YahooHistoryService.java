package com.bootcamp.bc_yahoo_finance.service;

import java.util.List;
import com.bootcamp.bc_yahoo_finance.dto.respDto.ExYahooAPI;
import com.bootcamp.bc_yahoo_finance.dto.respDto.ExYahooHistory;
import com.bootcamp.bc_yahoo_finance.entity.YahooStockEntity;
import com.bootcamp.bc_yahoo_finance.infra.ApiResp;

public interface YahooHistoryService {
  
  List<YahooStockEntity> getFromRedis(String key);

  void setFromRedis(List<YahooStockEntity> entities, String symbol);

  ExYahooHistory getYahooStockHistoryBySymbol(String symbol);

  ExYahooHistory getYahooStockHistoryBySymbol(String symbol, String interval);

  ApiResp<ExYahooHistory> getYahooStockHistoryBySymbol(String symbol,
      String period1, String period2, String interval);

void saveYahooHistory(String symbol, List<ExYahooAPI> exYahooAPIs);

ApiResp<YahooStockEntity> getAndSaveYahooHistroy(String symbol, String interval);

ApiResp<YahooStockEntity> saveFromYahooAPI(String symbol,
      String interval, String start);
}

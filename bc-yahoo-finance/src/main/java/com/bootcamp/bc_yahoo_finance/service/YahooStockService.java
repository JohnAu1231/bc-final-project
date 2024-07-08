package com.bootcamp.bc_yahoo_finance.service;

import com.bootcamp.bc_yahoo_finance.dto.respDto.ExYahooAPI;

public interface YahooStockService {
  
  ExYahooAPI getYahooStockBySymbol(String symbol);

  void saveYahooAPI(String symbol);

}

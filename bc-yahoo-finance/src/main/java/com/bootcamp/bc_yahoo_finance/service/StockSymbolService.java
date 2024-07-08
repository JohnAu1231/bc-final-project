package com.bootcamp.bc_yahoo_finance.service;

import com.bootcamp.bc_yahoo_finance.entity.StockRedisEntity;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface StockSymbolService {
  
  void saveStockSymbol();

  void deleteAll();

   StockRedisEntity getStockList() throws JsonProcessingException;

 
  }

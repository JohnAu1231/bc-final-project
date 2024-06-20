package com.bootcamp.bc_yahoo_finance.service;

import java.util.List;
import com.bootcamp.bc_yahoo_finance.entity.StockRedisEntity;
import com.bootcamp.bc_yahoo_finance.entity.StockSymbolEntity;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface StockSymbolService {
  
  void saveStockSymbol();

  void deleteAll();

   StockRedisEntity getStockList() throws JsonProcessingException;

 
  }

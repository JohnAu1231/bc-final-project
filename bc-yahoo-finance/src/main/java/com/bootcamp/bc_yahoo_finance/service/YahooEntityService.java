package com.bootcamp.bc_yahoo_finance.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface YahooEntityService {

  void saveYahooStockByStockList() throws JsonProcessingException;
  
  void saveStockFromYahoo() throws JsonProcessingException;
}

package com.bootcamp.bc_yahoo_finance.service;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.bootcamp.bc_yahoo_finance.dto.respDto.ExYahooAPI;
import com.bootcamp.bc_yahoo_finance.entity.YahooStockEntity;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface YahooStockService {
  
  ExYahooAPI getYahooStockBySymbol(String symbol);

  void saveYahooAPI(String symbol);

  // void saveYahooStockByStockList() throws JsonProcessingException;
}

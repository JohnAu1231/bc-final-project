package com.bootcamp.bc_yahoo_finance.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.bc_yahoo_finance.controller.StockSymbolOperation;
import com.bootcamp.bc_yahoo_finance.entity.StockRedisEntity;
import com.bootcamp.bc_yahoo_finance.entity.StockSymbolEntity;
import com.bootcamp.bc_yahoo_finance.service.StockSymbolService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class StockSymbolController implements StockSymbolOperation{
  @Autowired
  private StockSymbolService stockSymbolService;

  @Override
  public StockRedisEntity getStockSymbols() throws JsonProcessingException{
    return stockSymbolService.getStockList();
  }
}

package com.bootcamp.bc_yahoo_finance.controller;

import org.springframework.web.bind.annotation.GetMapping;
import com.bootcamp.bc_yahoo_finance.entity.StockRedisEntity;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface StockSymbolOperation {
  
    @GetMapping(value = "/stocksymbols")
 StockRedisEntity getStockSymbols() throws JsonProcessingException;
}

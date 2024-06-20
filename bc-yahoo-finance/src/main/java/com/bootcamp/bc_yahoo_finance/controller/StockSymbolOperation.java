package com.bootcamp.bc_yahoo_finance.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import com.bootcamp.bc_yahoo_finance.entity.StockRedisEntity;
import com.bootcamp.bc_yahoo_finance.entity.StockSymbolEntity;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface StockSymbolOperation {
  
    @GetMapping(value = "/stocksymbols")
 StockRedisEntity getStockSymbols() throws JsonProcessingException;
}

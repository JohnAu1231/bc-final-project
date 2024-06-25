package com.bootcamp.bc_stock_web.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.bootcamp.bc_stock_web.model.StockPrice;

public interface StockPriceOperation {

 @GetMapping(value = "/five-minutes/{symbol}")
 List<StockPrice> getFiveMinuteData(@PathVariable String symbol);
  
}

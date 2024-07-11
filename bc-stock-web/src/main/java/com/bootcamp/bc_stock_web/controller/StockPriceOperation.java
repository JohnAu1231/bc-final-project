package com.bootcamp.bc_stock_web.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.bootcamp.bc_stock_web.dto.reqdto.YahooStockDTO;
import com.bootcamp.bc_stock_web.dto.respdto.ExFiveMinList;
import com.bootcamp.bc_stock_web.infra.ApiResp;
import com.bootcamp.bc_stock_web.model.StockPrice;

public interface StockPriceOperation {

 @GetMapping(value = "/five-minutes/{symbol}")
 List<StockPrice> getFiveMinuteData(@PathVariable String symbol);
  
 @GetMapping(value = "/five-minutes-list/{symbol}")
 List<YahooStockDTO> getFiveMinuteList(@PathVariable String symbol);

 @GetMapping(value = "/history/{symbol}/{interval}")
 ApiResp<ExFiveMinList> getHistoryStock(@PathVariable String symbol, @PathVariable String interval);
}

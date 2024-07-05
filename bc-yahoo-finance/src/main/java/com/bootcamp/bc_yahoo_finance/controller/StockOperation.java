package com.bootcamp.bc_yahoo_finance.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.bootcamp.bc_yahoo_finance.dto.reqDto.FiveMinListDTO;
import com.bootcamp.bc_yahoo_finance.dto.reqDto.YahooStockDTO;
import com.bootcamp.bc_yahoo_finance.dto.respDto.ExYahooAPI;
import com.bootcamp.bc_yahoo_finance.dto.respDto.ExYahooHistory;
import com.bootcamp.bc_yahoo_finance.entity.StockSymbolEntity;
import com.bootcamp.bc_yahoo_finance.entity.SystemDate;
import com.bootcamp.bc_yahoo_finance.entity.YahooStockEntity;
import com.bootcamp.bc_yahoo_finance.exception.RedisBuildingException;
import com.bootcamp.bc_yahoo_finance.infra.ApiResp;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface StockOperation {
  
  @GetMapping(value = "/stock/{symbol}")
  ExYahooAPI getYahooAPI(@PathVariable String symbol);

  @GetMapping(value = "/stock/test/{symbol}")
  YahooStockEntity getYahooStock(@PathVariable String symbol);

  @GetMapping(value = "/stock/test2/{symbol}")
  YahooStockDTO getYahooStockDTO(@PathVariable String symbol);

  @GetMapping(value = "/save")
  void saveYahooStockByStockList() throws JsonProcessingException;

  @GetMapping(value = "/systemdate/{symbol}")
  String getSystemDate(@PathVariable String symbol);

  @GetMapping(value = "/five/{symbol}")
  FiveMinListDTO getFiveMinListDTO(@PathVariable String symbol) throws RedisBuildingException;

  @GetMapping(value = "/history/{symbol}")
  FiveMinListDTO getYahooStockHistoryBySymbol(@PathVariable String symbol);

  @GetMapping(value = "/history/{symbol}/{interval}")
   ApiResp<FiveMinListDTO>  getYahooStockHistoryBySymbol(@PathVariable String symbol, @PathVariable String interval);
  

}

package com.bootcamp.bc_yahoo_finance.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.bc_yahoo_finance.controller.StockOperation;
import com.bootcamp.bc_yahoo_finance.controller.StockSymbolOperation;
import com.bootcamp.bc_yahoo_finance.dto.reqDto.FiveMinListDTO;
import com.bootcamp.bc_yahoo_finance.dto.reqDto.YahooStockDTO;
import com.bootcamp.bc_yahoo_finance.dto.respDto.ExYahooAPI;
import com.bootcamp.bc_yahoo_finance.dto.respDto.ExYahooAPI.QuoteResponse.ExYahooStock;
import com.bootcamp.bc_yahoo_finance.entity.StockRedisEntity;
import com.bootcamp.bc_yahoo_finance.entity.SystemDate;
import com.bootcamp.bc_yahoo_finance.entity.YahooStockEntity;
import com.bootcamp.bc_yahoo_finance.exception.RedisBuildingException;
import com.bootcamp.bc_yahoo_finance.mapper.ExYahooAPIMapper;
import com.bootcamp.bc_yahoo_finance.mapper.YahooStockEntityMapper;
import com.bootcamp.bc_yahoo_finance.mapper.YahooSymbolMapper;
import com.bootcamp.bc_yahoo_finance.service.FiveMinListService;
import com.bootcamp.bc_yahoo_finance.service.SystemDateService;
import com.bootcamp.bc_yahoo_finance.service.YahooEntityService;
import com.bootcamp.bc_yahoo_finance.service.YahooStockService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class StockController implements StockOperation{
  
  @Autowired
  private YahooStockService yahooStockService;

  @Autowired 
  private StockSymbolOperation stockSymbolOperation;

  @Autowired
  private YahooSymbolMapper yahooSymbolMapper;

  @Autowired
  private SystemDateService systemDateService;

  @Autowired
  private YahooStockEntityMapper yahooStockEntityMapper;

  @Autowired 
  private ExYahooAPIMapper exYahooAPIMapper;

  @Autowired
  private YahooEntityService yahooEntityService;

  @Autowired
  private FiveMinListService fiveMinListService;
  

  @Override
  public ExYahooAPI getYahooAPI(String symbol) {
    return yahooStockService.getYahooStockBySymbol(symbol);
  }

  @Override
  public YahooStockEntity getYahooStock(String symbol) {
    return exYahooAPIMapper.mapToYahooFinanceEntity(this.getYahooAPI(symbol));
  }

  @Override
  public YahooStockDTO getYahooStockDTO(String symbol) {
    return yahooStockEntityMapper.mapToYahooStockDTO((exYahooAPIMapper.mapToYahooFinanceEntity(this.getYahooAPI(symbol))));
  }
  
  @Override
  public void saveYahooStockByStockList() throws JsonProcessingException {
    yahooEntityService.saveYahooStockByStockList();
  }

  @Override
  public String getSystemDate(String symbol) {
    return systemDateService.getSystemData(symbol);
  }

  @Override
  public FiveMinListDTO getFiveMinListDTO(String symbol) throws RedisBuildingException {
    return fiveMinListService.getFiveMinData(symbol);
  }
}

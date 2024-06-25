package com.bootcamp.bc_stock_web.controller.impl;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.bc_stock_web.controller.StockPriceOperation;
import com.bootcamp.bc_stock_web.dto.respdto.ExFiveMinList;
import com.bootcamp.bc_stock_web.mapper.YahooStockDTOMapper;
import com.bootcamp.bc_stock_web.model.StockPrice;
import com.bootcamp.bc_stock_web.service.FiveMinListService;


@RestController
public class StockPriceController implements StockPriceOperation {

  @Autowired
  private FiveMinListService fiveMinListService;

  @Autowired
  private YahooStockDTOMapper yahooStockDTOMapper;

  public List<StockPrice> getFiveMinuteData(String symbol) {
    
    ExFiveMinList ls = fiveMinListService.getFiveMinList(symbol);
    return ls.getData().stream().map(e -> yahooStockDTOMapper.mapToStockPrice(e)).collect(Collectors.toList());

  }
}
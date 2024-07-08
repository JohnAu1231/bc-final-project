package com.bootcamp.bc_stock_web.controller.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.bc_stock_web.controller.MovingAverageOperation;
import com.bootcamp.bc_stock_web.mapper.YahooStockDTOMapper;
import com.bootcamp.bc_stock_web.model.StockPrice;
import com.bootcamp.bc_stock_web.service.MovingAverageService;

@RestController
public class MovingAverageController implements MovingAverageOperation{
  
  @Autowired
  private MovingAverageService movingAverageService;

  @Autowired
  private YahooStockDTOMapper yahooStockDTOMapper;

  public List<StockPrice> getHourMovingAverage(String symbol) {
    return movingAverageService.getHourAveragePoint(symbol).stream()
    .map(e -> yahooStockDTOMapper.mapToStockPrice(e)).collect(Collectors.toList());
  }

  public List<StockPrice> getDayMovingAverage(String symbol,String period) {
    return movingAverageService.getHourAveragePoint(symbol).stream()
    .map(e -> yahooStockDTOMapper.mapToStockPrice(e)).collect(Collectors.toList());
  }




}

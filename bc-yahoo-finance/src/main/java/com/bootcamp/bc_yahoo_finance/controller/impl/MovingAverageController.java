package com.bootcamp.bc_yahoo_finance.controller.impl;

import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.bc_yahoo_finance.controller.MovingAverageOperation;
import com.bootcamp.bc_yahoo_finance.dto.reqDto.MovingAverageDTO;
import com.bootcamp.bc_yahoo_finance.dto.reqDto.YahooStockDTO;
import com.bootcamp.bc_yahoo_finance.service.MovingAverageService;

@RestController
public class MovingAverageController implements MovingAverageOperation{
  
  @Autowired
  private MovingAverageService movingAverageService;

  public List<YahooStockDTO> getHourMovingAvergae(String symbol) {
    return movingAverageService.getMovingAverageNearlyHour(symbol);
  }

  public List<MovingAverageDTO> getHourMovingAvergaeByDay(String symbol, int period) {
    return movingAverageService.getMovingAverageByDay(symbol, period);
  }
}

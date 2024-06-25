package com.bootcamp.bc_yahoo_finance.service;

import java.util.List;
import com.bootcamp.bc_yahoo_finance.dto.reqDto.MovingAverageDTO;
import com.bootcamp.bc_yahoo_finance.dto.reqDto.YahooStockDTO;

public interface MovingAverageService {
  
  List<YahooStockDTO> getMovingAverageNearlyHour(String symbol);
}

package com.bootcamp.bc_stock_web.service;

import java.util.List;
import com.bootcamp.bc_stock_web.dto.reqdto.YahooStockDTO;

public interface MovingAverageService {
  
  List<YahooStockDTO> getHourAveragePoint(String symbol, String period);

  List<YahooStockDTO> getDayAveragePoint(String symbol, String period);

}

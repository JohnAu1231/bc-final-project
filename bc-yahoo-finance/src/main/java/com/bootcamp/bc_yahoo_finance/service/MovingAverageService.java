package com.bootcamp.bc_yahoo_finance.service;

import java.util.List;
import com.bootcamp.bc_yahoo_finance.dto.reqDto.MovingAverageDTO;
import com.bootcamp.bc_yahoo_finance.dto.reqDto.YahooStockDTO;
import com.bootcamp.bc_yahoo_finance.entity.YahooStockEntity;

public interface MovingAverageService {

  List<YahooStockDTO> getMovingAverageNearlyHour(String symbol, int period);

  List<MovingAverageDTO> getMovingAverageByDay(String symbol, int period);

  List<MovingAverageDTO> getMovingAverageByPeriodByDataType(String symbol,
      int period, String dataType);

  List<MovingAverageDTO> calculateSMAByClose(String symbol, int period,
      String dataType, List<YahooStockEntity> entityList);

  List<MovingAverageDTO> calculateSMAByPrice(String symbol, int period,
      String dataType, List<YahooStockEntity> entityList);
}

package com.bootcamp.bc_stock_web.mapper;

import java.time.LocalDateTime;
import org.springframework.stereotype.Component;
import com.bootcamp.bc_stock_web.dto.reqdto.YahooStockDTO;
import com.bootcamp.bc_stock_web.model.StockPrice;

@Component
public class YahooStockDTOMapper {
  
  public StockPrice mapToStockPrice(YahooStockDTO yahooStockDTO) {
    LocalDateTime time = yahooStockDTO.getMarketTime();
    Double price = yahooStockDTO.getRegularMarketPrice();
    return new StockPrice(time, price);
  }
}

package com.bootcamp.bc_yahoo_finance.mapper;

import org.springframework.stereotype.Component;
import com.bootcamp.bc_yahoo_finance.dto.respDto.ExStock;

@Component
public class ExStockMapper {
  public ExStock mapToExStock(String symbol) {
    return ExStock.builder().symbol(symbol).build();
  }
}

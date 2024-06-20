package com.bootcamp.bc_yahoo_finance.mapper;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.bootcamp.bc_yahoo_finance.dto.reqDto.YahooStockDTO;
import com.bootcamp.bc_yahoo_finance.dto.respDto.ExStock;
import com.bootcamp.bc_yahoo_finance.dto.respDto.ExYahooAPI;
import com.bootcamp.bc_yahoo_finance.dto.respDto.ExYahooAPI.QuoteResponse.ExYahooStock;
import com.bootcamp.bc_yahoo_finance.entity.StockRedisEntity;
import com.bootcamp.bc_yahoo_finance.entity.StockSymbolEntity;
import com.bootcamp.bc_yahoo_finance.entity.YahooStockEntity;

@Component
public class YahooSymbolMapper {

  public StockSymbolEntity mapStocksEntity(ExStock stock) {
    return StockSymbolEntity.builder().symbol(stock.getSymbol()).build();
  }

  public StockRedisEntity mapToStockRedisEntity(List<StockSymbolEntity> stocks) {

    List<String> symbolList = new ArrayList<>();
    stocks.stream().forEach(e -> symbolList.add(e.getSymbol()));
    return StockRedisEntity.builder().symbolList(symbolList).build();
  }
  
}

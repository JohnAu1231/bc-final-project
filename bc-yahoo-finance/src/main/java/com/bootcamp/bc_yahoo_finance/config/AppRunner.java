package com.bootcamp.bc_yahoo_finance.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.bootcamp.bc_yahoo_finance.service.StockSymbolService;
@Component
public class AppRunner implements CommandLineRunner {

  @Autowired
  private StockSymbolService stocksService;

  @Override
  public void run(String... args) {
    stocksService.deleteAll();
    stocksService.saveStockSymbol();
    
  }
}

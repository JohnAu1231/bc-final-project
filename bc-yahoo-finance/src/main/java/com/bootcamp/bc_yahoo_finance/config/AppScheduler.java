package com.bootcamp.bc_yahoo_finance.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.bootcamp.bc_yahoo_finance.service.YahooEntityService;
import com.fasterxml.jackson.core.JsonProcessingException;

@EnableAsync
@Component
public class AppScheduler {
  
  @Autowired
  private YahooEntityService yahooEntityService;

  @Scheduled(cron = "0 0/5 8-18 ? * MON-FRI", zone = "GMT+8:00")
 
  public void saveYahooStockEveryFiveMins() throws JsonProcessingException{
    yahooEntityService.saveYahooStockByStockList();
  }

}

package com.bootcamp.bc_yahoo_finance.config;

import java.time.LocalDateTime;
import java.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.bootcamp.bc_yahoo_finance.service.YahooEntityService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;

@EnableAsync
@Component
@Slf4j
public class AppScheduler {
  
  @Autowired
  private YahooEntityService yahooEntityService;

  @Scheduled(cron = "0 0/5 8-18 ? * MON-FRI", zone = "GMT+8:00")
 
  public void saveYahooStockEveryFiveMins() throws JsonProcessingException{
    yahooEntityService.saveYahooStockByStockList();

        if (LocalTime.now().isAfter(LocalTime.of(16, 30))) {
         
            return;
        }

        log.debug("This method runs every 5 minutes during business hours.");
  }

}

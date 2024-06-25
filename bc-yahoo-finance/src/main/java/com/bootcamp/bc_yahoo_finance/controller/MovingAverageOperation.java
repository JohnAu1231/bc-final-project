package com.bootcamp.bc_yahoo_finance.controller;

import java.sql.Timestamp;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.bootcamp.bc_yahoo_finance.dto.reqDto.MovingAverageDTO;
import com.bootcamp.bc_yahoo_finance.dto.reqDto.YahooStockDTO;
import jakarta.websocket.server.PathParam;

public interface MovingAverageOperation {
  
  @GetMapping(value = "/hourMA/{symbol}")
   List<YahooStockDTO> getHourMovingAvergae(@PathVariable String symbol);
}

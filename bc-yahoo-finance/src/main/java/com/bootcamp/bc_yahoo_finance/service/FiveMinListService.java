package com.bootcamp.bc_yahoo_finance.service;

import com.bootcamp.bc_yahoo_finance.dto.reqDto.FiveMinListDTO;

public interface FiveMinListService {

  FiveMinListDTO getFiveMinData(String symbol, Long start, Long end);

  FiveMinListDTO getFiveMinDataToday(String symbol);

  FiveMinListDTO getFiveMinDataFromStartToEnd(String symbol, Long start, Long end);
  
  FiveMinListDTO getFiveMinDataAllTime(String symbol);
}

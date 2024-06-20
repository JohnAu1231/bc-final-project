package com.bootcamp.bc_yahoo_finance.service;

import com.bootcamp.bc_yahoo_finance.dto.reqDto.FiveMinListDTO;
import com.bootcamp.bc_yahoo_finance.exception.RedisBuildingException;

public interface FiveMinListService {

FiveMinListDTO getFiveMinData(String symbol)  throws RedisBuildingException;
  
}

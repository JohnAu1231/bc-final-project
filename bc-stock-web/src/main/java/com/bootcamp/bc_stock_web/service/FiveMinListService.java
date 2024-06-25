package com.bootcamp.bc_stock_web.service;

import com.bootcamp.bc_stock_web.dto.respdto.ExFiveMinList;

public interface FiveMinListService {
  
  ExFiveMinList getFiveMinList(String symbol);
}

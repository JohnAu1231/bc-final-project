package com.bootcamp.bc_stock_web.controller;

import com.bootcamp.bc_stock_web.dto.respdto.ExFiveMinList;

public interface FiveMinListOperation {

  ExFiveMinList getFiveMinListAllTime(String symbol);
  
}

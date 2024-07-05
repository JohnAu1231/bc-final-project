package com.bootcamp.bc_stock_web.service;

import com.bootcamp.bc_stock_web.dto.respdto.ExFiveMinList;
import com.bootcamp.bc_stock_web.infra.ApiResp;

public interface FiveMinListService {
  
  ExFiveMinList getFiveMinList(String symbol);

  ApiResp<ExFiveMinList> getHistoryList(String symbol, String interval);
}

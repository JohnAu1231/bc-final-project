package com.bootcamp.bc_stock_web.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.bc_stock_web.controller.FiveMinListOperation;
import com.bootcamp.bc_stock_web.dto.respdto.ExFiveMinList;
import com.bootcamp.bc_stock_web.service.FiveMinListService;

@RestController
public class FiveMinListController implements FiveMinListOperation{
  
  @Autowired
  private FiveMinListService fiveMinListService;

  public ExFiveMinList getFiveMinList(String symbol) {
    return fiveMinListService.getFiveMinList(symbol);
  }
}

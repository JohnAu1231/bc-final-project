package com.bootcamp.bc_stock_web.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.bootcamp.bc_stock_web.controller.FiveMinListOperation;

@Controller
public class StockViewController {
  
  @Autowired
  private FiveMinListOperation fiveMinListOperation;

  @GetMapping(value = "/stock/{symbol}")
  public String stock(Model model, @PathVariable String symbol) {
    model.addAttribute("message", fiveMinListOperation.getFiveMinListAllTime(symbol) );
    return "mainpage";
  }
}

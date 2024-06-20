package com.bootcamp.bc_yahoo_finance.model;

import java.util.List;
import java.util.Map;
import lombok.Getter;

@Getter
public class StockList {
  
  private Map<String, List<String>> stocksList;
}

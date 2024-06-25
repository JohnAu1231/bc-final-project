package com.bootcamp.bc_yahoo_finance.entity;

import java.util.List;
import java.util.Map;
import jakarta.persistence.Entity;

public class FiveMinListEntity {
  

  private Long marketTime;
  private List<YahooStockEntity> data;
}

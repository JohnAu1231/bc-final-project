package com.bootcamp.bc_yahoo_finance.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TSTOCK_QUOTE_YAHOO")
public class YahooStockEntity implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) //
  private Long id;
  private String symbol;
  @Column(name = "market_time")
  private Long regularMarketUnix;
  private Double marketPrice;
  private Double regularMarketChangePercent;
  private Double bid;
  private Double bidSize;
  private Double ask;
  private Double askSize;

}

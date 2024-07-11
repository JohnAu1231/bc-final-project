package com.bootcamp.bc_yahoo_finance.entity;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@IdClass(YahooStockEntityPK.class) 
@Table(name = "TSTOCK_QUOTE_YAHOO")
public class YahooStockEntity implements Serializable {
  @Id
  private String symbol;
  @Id
  @Column(name = "market_time")
  private Long regularMarketUnix;
  @Id
  private String dataType;
  private Double marketPrice;
  private Double regularMarketChangePercent;
  private Double bid;
  private Double bidSize;
  private Double ask;
  private Double askSize;
  private Long volume;
  private Double close;
  private Double low;
  private Double open;
  private Double high;
  

}

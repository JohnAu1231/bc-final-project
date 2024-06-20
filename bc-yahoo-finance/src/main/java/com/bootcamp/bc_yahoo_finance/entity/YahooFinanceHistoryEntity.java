package com.bootcamp.bc_yahoo_finance.entity;

import java.io.Serializable;
import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "YahooHistory")
public class YahooFinanceHistoryEntity implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) //
  private Long id;
  private LocalDate date;
  private Double open;
  private Double high;
  private Double low;
  private Double close;
  private Double adjClose;
  private Long volume;
}

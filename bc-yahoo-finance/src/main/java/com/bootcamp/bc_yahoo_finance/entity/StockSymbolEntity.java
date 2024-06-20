package com.bootcamp.bc_yahoo_finance.entity;

import java.io.Serializable;
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
@Table(name = "tstocks")
public class StockSymbolEntity implements Serializable{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) //
  private Long id;
  private String symbol;
}

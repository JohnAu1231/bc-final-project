package com.bootcamp.bc_yahoo_finance.entity;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StockRedisEntity implements Serializable{

  @JsonProperty(value = "STOCK-LIST")
  private List<String> symbolList;

}



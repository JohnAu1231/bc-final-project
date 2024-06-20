package com.bootcamp.bc_yahoo_finance.dto.reqDto;

import java.util.List;
import com.bootcamp.bc_yahoo_finance.entity.YahooStockEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FiveMinListDTO {
  
  private Long regularMarketTIme;
  private List<YahooStockDTO> data;
}

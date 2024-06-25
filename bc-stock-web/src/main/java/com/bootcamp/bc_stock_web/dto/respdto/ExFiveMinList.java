package com.bootcamp.bc_stock_web.dto.respdto;

import java.util.List;
import com.bootcamp.bc_stock_web.dto.reqdto.YahooStockDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Builder
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ExFiveMinList {
  
  private Long regularMarketTIme;
  private List<YahooStockDTO> data;

}

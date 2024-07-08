package com.bootcamp.bc_yahoo_finance.dto.reqDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MovingAverageDTO {

  private Long timestamp;
  private Double regularMarketPrice;
}

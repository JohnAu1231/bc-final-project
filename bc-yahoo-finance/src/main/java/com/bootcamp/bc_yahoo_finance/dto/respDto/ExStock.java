package com.bootcamp.bc_yahoo_finance.dto.respDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ExStock {

  private String symbol;
}

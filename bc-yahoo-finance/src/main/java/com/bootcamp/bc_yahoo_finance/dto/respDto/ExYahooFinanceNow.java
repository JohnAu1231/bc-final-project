package com.bootcamp.bc_yahoo_finance.dto.respDto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExYahooFinanceNow {
  
  @JsonProperty(value = "regularMarketPrice")
  private Double nowPrice;
  @JsonProperty(value = "regularMarketDayLow")
  private Double dayLow;
  @JsonProperty(value = "regularMarketDayHigh")
  private Double dayHigh;
  @JsonProperty(value = "regularMarketVolume")
  private Long dayVolume;

}

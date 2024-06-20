package com.bootcamp.bc_yahoo_finance.dto.respDto;

import java.sql.Timestamp;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExYahooAPI {
  private QuoteResponse quoteResponse;

  @Getter
  @AllArgsConstructor
  @NoArgsConstructor
  @Builder
  public static class QuoteResponse {
    private List<ExYahooStock> result;
    private String error;

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ExYahooStock {
   
      private String symbol;
      @JsonProperty(value = "regularMarketPrice")
      private Double marketPrice;
      @JsonProperty(value = "regularMarketDayLow")
      private Double dayLow;
      @JsonProperty(value = "regularMarketDayHigh")
      private Double dayHigh;
      @JsonProperty(value = "regularMarketVolume")
      private Long dayVolume;
      private Long regularMarketTime;
      private Double regularMarketChangePercent;
      private Double bid;
      private Double bidSize;
      private Double ask;
      private Double askSize;
      
    }
  }



}

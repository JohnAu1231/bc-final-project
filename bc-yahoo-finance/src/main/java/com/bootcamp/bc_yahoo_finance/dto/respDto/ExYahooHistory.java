package com.bootcamp.bc_yahoo_finance.dto.respDto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ExYahooHistory {

  private YahooChart chart;

  @Getter
  @AllArgsConstructor
  @NoArgsConstructor
  @ToString
  public static class YahooChart {

    private List<YahooResult> result;

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class YahooResult {

      private YahooMeta meta;
      private List<Long> timestamp;
      private YahooIndicators indicators;

      @Getter
      @AllArgsConstructor
      @NoArgsConstructor
      @ToString
      public static class YahooMeta {
        private String currency;
        private String symbol;
        private String exchangeName;
        private String fullExchangeName;
        private String instrumentType;
        private long firstTradeDate;
        private long regularMarketTime;
        private boolean hasPrePostMarketData;
        private int gmtoffset;
        private String timezone;
        private String exchangeTimezoneName;
        private double regularMarketPrice;
        private double fiftyTwoWeekHigh;
        private double fiftyTwoWeekLow;
        private double regularMarketDayHigh;
        private double regularMarketDayLow;
        private long regularMarketVolume;
        private double chartPreviousClose;
        private int priceHint;
        private CurrentTradingPeriod currentTradingPeriod;
        private String dataGranularity;
        private String range;
        private List<String> validRanges;

        @Getter
        @AllArgsConstructor
        @NoArgsConstructor
        @ToString
        public static class CurrentTradingPeriod {
          private PreMarket pre;
          private RegularMarket regular;
          private PostMarket post;

          @Getter
          @AllArgsConstructor
          @NoArgsConstructor
          @ToString
          public static class PreMarket {
            private String timezone;
            private long start;
            private long end;
            private int gmtoffset;
          }

          @Getter
          @AllArgsConstructor
          @NoArgsConstructor
          @ToString
          public static class RegularMarket {
            private String timezone;
            private long start;
            private long end;
            private int gmtoffset;
          }

          @Getter
          @AllArgsConstructor
          @NoArgsConstructor
          @ToString
          public static class PostMarket {
            private String timezone;
            private long start;
            private long end;
            private int gmtoffset;
          }
        }
      }

      @Getter
      @AllArgsConstructor
      @NoArgsConstructor
      @ToString
      public static class YahooIndicators {

        private List<YahooQuote> quote;
        private List<AdjClose> adjclose;

        @Getter
        @AllArgsConstructor
        @NoArgsConstructor
        @ToString
        public static class YahooQuote {

          private List<Long> volume;
          private List<Double> close;
          private List<Double> low;
          private List<Double> open;
          private List<Double> high;
        }

        @Getter
        @AllArgsConstructor
        @NoArgsConstructor
        @ToString
        public static class AdjClose {

          private List<Double> adjclose;
        }
      }
    }
  }
}

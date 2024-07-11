package com.bootcamp.bc_yahoo_finance.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bootcamp.bc_yahoo_finance.dto.reqDto.MovingAverageDTO;
import com.bootcamp.bc_yahoo_finance.dto.reqDto.YahooStockDTO;
import com.bootcamp.bc_yahoo_finance.entity.YahooStockEntity;
import com.bootcamp.bc_yahoo_finance.infra.NotFoundException;
import com.bootcamp.bc_yahoo_finance.infra.TimeStampConverter;
import com.bootcamp.bc_yahoo_finance.repository.YahooStockRepository;
import com.bootcamp.bc_yahoo_finance.service.MovingAverageService;


@Service
public class MovingAverageServiceImpl implements MovingAverageService {

  @Autowired
  private YahooStockRepository yahooStockRepository;

  public List<YahooStockDTO> getMovingAverageNearlyHour(String symbol,
      int period) {
    List<YahooStockEntity> entityList = yahooStockRepository
        .findAllStockBySymbolByDate(symbol,
            TimeStampConverter.startOf(LocalDateTime.now()).getTime(),
            TimeStampConverter.endOf(LocalDateTime.now()).getTime(), "day")
        .get();
    if (entityList.isEmpty()) {
      Long lastTimeValue = yahooStockRepository
          .findMaxMarketTimeBySymbolAndDataType(symbol, "day").get();

      entityList =
          yahooStockRepository
              .findAllStockBySymbolByDate(symbol,
                  TimeStampConverter.startOf(lastTimeValue).getTime(),
                  TimeStampConverter.endOf(lastTimeValue).getTime(), "day")
              .get();
    }
    if (entityList.isEmpty()) {
      throw new NotFoundException();
    }
    List<YahooStockDTO> yahooStockDTOs = new ArrayList<>();
    for (int i = 0; i < entityList.size(); i++) {

      BigDecimal sum = BigDecimal.valueOf(0);
      int count = 0;
      int limit = i - period * 12 + 1;
      if (limit < 0) {
        limit = 0;
      }
      for (int j = i; j >= limit; j--) {
        Double price = 0d;
        // long timeDifference = entityList.get(i).getRegularMarketUnix() - entityList.get(j).getRegularMarketUnix();
        // check null
        if (entityList.get(i).getRegularMarketUnix() != null
            && entityList.get(j).getRegularMarketUnix() != null
            && entityList.get(j).getMarketPrice() != null
            && entityList.get(j).getMarketPrice() != 0) {
          price = entityList.get(j).getMarketPrice();

          sum = sum.add(BigDecimal.valueOf(price));
          count++;
          continue;

        }
        // if null
        if (entityList.get(j).getMarketPrice() == null
            || entityList.get(j).getMarketPrice() == 0) {
          // get last value first,
          for (int k = i - j; k >= 0; k--) {
            if (entityList.get(k).getMarketPrice() != null) {
              price = entityList.get(k).getMarketPrice();
              break;
            }
          }
          // if still no value, get after point
          if (price == 0) {
            for (int k = i - j; k < entityList.size(); k++) {
              if (entityList.get(k).getMarketPrice() != null) {
                price = entityList.get(k).getMarketPrice();
                break;
              }
            }
          }
        }
        // if no value, something wrong throw error
        if (price == null) {
          throw new NotFoundException();
        }


        sum = sum.add(BigDecimal.valueOf(price));
        count++;
      }

      BigDecimal average = BigDecimal.valueOf(0);
      if (sum.doubleValue() != 0) {
        average =
            sum.divide(BigDecimal.valueOf(count), 2, RoundingMode.HALF_UP);
      }
      
      Timestamp timestamp =
          new Timestamp(entityList.get(i).getRegularMarketUnix().longValue());
      LocalDateTime time = Instant.ofEpochSecond(timestamp.getTime())
          .atZone(ZoneId.of("Asia/Hong_Kong")).toLocalDateTime();
      yahooStockDTOs.add(YahooStockDTO.builder().marketTime(time)
          .regularMarketUnix(timestamp.getTime())
          .regularMarketPrice(average.doubleValue()).build());
    }
    return yahooStockDTOs;
  }

  @Override
  public List<MovingAverageDTO> getMovingAverageByDay(String symbol,
      int period) {
    return this.getMovingAverageByPeriodByDataType(symbol, period, "history1d");
  }

  @Override
  public List<MovingAverageDTO> getMovingAverageByPeriodByDataType(
      String symbol, int period, String dataType) {

    Optional<List<YahooStockEntity>> optionalEntityList =
        yahooStockRepository.findAllStockBySymbolAndDataType(symbol, dataType);

    if (!optionalEntityList.isPresent() || optionalEntityList.get().isEmpty()) {
      return null;
    }

    List<YahooStockEntity> entityList = optionalEntityList.get();
    return calculateSMAByClose(symbol, period, dataType, entityList);
  }

  @Override
  public List<MovingAverageDTO> calculateSMAByClose(String symbol, int period,
      String dataType, List<YahooStockEntity> entityList) {
    List<MovingAverageDTO> movingAverageDTOs = new ArrayList<>();

    for (int i = 0; i < entityList.size(); i++) {
      double price = 0;
      if (entityList.get(i).getClose() != null) {
        price = entityList.get(i).getClose();
      }
      BigDecimal sum = BigDecimal.valueOf(price);
      int count = 1;
      Long time = entityList.get(i).getRegularMarketUnix();
      if (i < period) {
        for (int j = 1; j <= i; j++) {
          if (i - j < 0) {
            break;
          }
          if (entityList.get(i - j).getClose() != null) {
            price = entityList.get(i - j).getClose();
          }

          sum = sum.add(BigDecimal.valueOf(price));
          count++;
        }
        BigDecimal average =
            sum.divide(BigDecimal.valueOf(count), 4, RoundingMode.HALF_UP);
        movingAverageDTOs.add(MovingAverageDTO.builder().timestamp(time)
            .regularMarketPrice(average.doubleValue()).build());
      } else {
        for (int j = 0; j < period; j++) {
          if (i - j <= 0) {
            break;
          }
          if (entityList.get(i - j).getClose() != null) {
            price = entityList.get(i - j).getClose();
          }
          sum = sum.add(BigDecimal.valueOf(price));
          count++;
        }
        BigDecimal average =
            sum.divide(BigDecimal.valueOf(count), 4, RoundingMode.HALF_UP);
        movingAverageDTOs.add(MovingAverageDTO.builder().timestamp(time)
            .regularMarketPrice(average.doubleValue()).build());
      }
    }
    return movingAverageDTOs;
  }


  @Override
  public List<MovingAverageDTO> calculateSMAByPrice(String symbol, int period,
      String dataType, List<YahooStockEntity> entityList) {
    List<MovingAverageDTO> movingAverageDTOs = new ArrayList<>();

    for (int i = 0; i < entityList.size(); i++) {
      double price = 0;
      if (entityList.get(i).getClose() != null) {
        price = entityList.get(i).getClose();
      }
      BigDecimal sum = BigDecimal.valueOf(price);
      int count = 1;
      Long time = entityList.get(i).getRegularMarketUnix();
      if (i < period) {
        for (int j = 1; j <= i; j++) {
          if (i - j < 0) {
            break;
          }
          if (entityList.get(i - j).getClose() != null) {
            price = entityList.get(i - j).getClose();
          }

          sum = sum.add(BigDecimal.valueOf(price));
          count++;
        }
        BigDecimal average =
            sum.divide(BigDecimal.valueOf(count), 4, RoundingMode.HALF_UP);
        movingAverageDTOs.add(MovingAverageDTO.builder().timestamp(time)
            .regularMarketPrice(average.doubleValue()).build());
      } else {
        for (int j = 0; j < period; j++) {
          if (i - j <= 0) {
            break;
          }
          if (entityList.get(i - j).getClose() != null) {
            price = entityList.get(i - j).getClose();
          }
          sum = sum.add(BigDecimal.valueOf(price));
          count++;
        }
        BigDecimal average =
            sum.divide(BigDecimal.valueOf(count), 4, RoundingMode.HALF_UP);
        movingAverageDTOs.add(MovingAverageDTO.builder().timestamp(time)
            .regularMarketPrice(average.doubleValue()).build());
      }
    }
    return movingAverageDTOs;
  }


}

package com.bootcamp.bc_yahoo_finance.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bootcamp.bc_yahoo_finance.dto.reqDto.MovingAverageDTO;
import com.bootcamp.bc_yahoo_finance.dto.reqDto.YahooStockDTO;
import com.bootcamp.bc_yahoo_finance.entity.YahooStockEntity;
import com.bootcamp.bc_yahoo_finance.infra.LocalDateTimeConverter;
import com.bootcamp.bc_yahoo_finance.repository.YahooStockRepository;
import com.bootcamp.bc_yahoo_finance.service.MovingAverageService;

@Service
public class MovingAverageServiceImpl implements MovingAverageService {

  @Autowired
  private YahooStockRepository yahooStockRepository;

  public List<YahooStockDTO> getMovingAverageNearlyHour(String symbol) {
    List<YahooStockEntity> entityList =
        yahooStockRepository.findAllStockBySymbol(symbol).orElse(null);
    List<YahooStockDTO> yahooStockDTOs = new ArrayList<>();
    for (int i = 0; i < entityList.size(); i++) {
      BigDecimal sum = BigDecimal.valueOf(entityList.get(i).getMarketPrice());
      int count = 1;
      for (int j = 1; j < 13; j++) {
        if (i + j >= entityList.size()) {
          break;
        }

        long timeDifference = entityList.get(i).getRegularMarketUnix()
            - entityList.get(i + j).getRegularMarketUnix();
        if (timeDifference < 3600) {
          sum = sum
              .add(BigDecimal.valueOf(entityList.get(i + j).getMarketPrice()));
          count++;
        } else {
          break;
        }
      }
        BigDecimal average =
            sum.divide(BigDecimal.valueOf(count), 2, RoundingMode.HALF_UP);
        Timestamp timestamp = new Timestamp(
            entityList.get(i).getRegularMarketUnix().longValue() * 1000L);
        LocalDateTime time = timestamp.toInstant()
            .atZone(ZoneId.systemDefault()).toLocalDateTime();
        yahooStockDTOs.add(YahooStockDTO.builder().marketTime(time)
            .regularMarketPrice(average.doubleValue()).build());
      
    }
    return yahooStockDTOs;
  }
}

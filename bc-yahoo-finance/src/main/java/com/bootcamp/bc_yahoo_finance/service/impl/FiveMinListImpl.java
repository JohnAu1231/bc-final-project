package com.bootcamp.bc_yahoo_finance.service.impl;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bootcamp.bc_yahoo_finance.dto.reqDto.FiveMinListDTO;
import com.bootcamp.bc_yahoo_finance.dto.reqDto.YahooStockDTO;
import com.bootcamp.bc_yahoo_finance.entity.YahooStockEntity;
import com.bootcamp.bc_yahoo_finance.exception.RedisBuildingException;
import com.bootcamp.bc_yahoo_finance.infra.NotFoundException;
import com.bootcamp.bc_yahoo_finance.infra.RedisHelper;
import com.bootcamp.bc_yahoo_finance.infra.TimeStampConverter;
import com.bootcamp.bc_yahoo_finance.mapper.YahooStockEntityMapper;
import com.bootcamp.bc_yahoo_finance.repository.YahooStockRepository;
import com.bootcamp.bc_yahoo_finance.service.FiveMinListService;
import com.bootcamp.bc_yahoo_finance.service.SystemDateService;
import com.fasterxml.jackson.core.JsonProcessingException;


@Service
public class FiveMinListImpl implements FiveMinListService {

  @Autowired
  private RedisHelper redisHelper;

  @Autowired
  private YahooStockRepository yahooStockRepository;

  @Autowired
  private YahooStockEntityMapper yahooStockEntityMapper;

  @Autowired
  private SystemDateService systemDateService;

  @Override
  public FiveMinListDTO getFiveMinData(String symbol, Long start, Long end) {

    List<YahooStockEntity> entityList = yahooStockRepository
        .findAllStockBySymbolByDate(symbol, start, end, "day").get();

    if (entityList.isEmpty()) {
      throw new NotFoundException();
    }
    List<YahooStockDTO> dtoList = entityList.stream()
        .map(e -> yahooStockEntityMapper.mapToYahooStockDTO(e))
        .collect(Collectors.toList());

    Long maxRegularMarketUnix = yahooStockRepository
        .findMaxMarketTimeBySymbolAndDataType(symbol, "day").get();
    FiveMinListDTO fiveMinList = FiveMinListDTO.builder().data(dtoList)
        .regularMarketTIme(maxRegularMarketUnix).build();
    return fiveMinList;
  }

  @Override
  public FiveMinListDTO getFiveMinDataToday(String symbol) {
    try {
      String name = "5MIN-".concat(symbol);
      FiveMinListDTO fiveMinList = redisHelper.get(name, FiveMinListDTO.class);

      // produce systemdate
      systemDateService.getSystemDate(symbol);
      // construct the sysdate key
      String systemdateKey = "SYSDATE-".concat(symbol);
      // get the systemdate(yyyy-MM-dd) in Redis
      String systemdate = redisHelper.get(systemdateKey, String.class);
      // String -> LocalDate -> LocalDateTime -> Long of Timestamp Unix
      LocalDate localDate = LocalDate.parse(systemdate);
      LocalDateTime localDateTime = localDate.atStartOfDay();
      Long date = TimeStampConverter.startOf(localDateTime).getTime();
      Long dateMax = TimeStampConverter.endOf(localDateTime).getTime();

      fiveMinList = this.getFiveMinData(symbol, date, dateMax);

      // get the max regularMarketUnix
      //Long maxRegularMarketUnix =
       //   yahooStockRepository.findMaxMarketTimeBySymbol(symbol).orElseThrow();
      redisHelper.set("5MIN-".concat(symbol), fiveMinList,
          Duration.ofHours(12L));
      return fiveMinList;
    } catch (JsonProcessingException e) {
      throw new RedisBuildingException(
          "JsonProcessingException in getFiveMinData method");
    }
  }


  @Override
  public FiveMinListDTO getFiveMinDataFromStartToEnd(String symbol, Long start,
      Long end) {
    // not save in redis, too many cases
    return this.getFiveMinData(symbol, start, end);

  }

  @Override
  public FiveMinListDTO getFiveMinDataAllTime(String symbol) {
    try {
      String name = "5MIN-Full".concat(symbol);
      FiveMinListDTO fiveMinList = redisHelper.get(name, FiveMinListDTO.class);

      Long now = TimeStampConverter.of(LocalDateTime.now()).getTime();
      fiveMinList = this.getFiveMinData(symbol, 1L, now);

      redisHelper.set("5MIN-Full".concat(symbol), fiveMinList,
          Duration.ofHours(12L));
      return fiveMinList;
    } catch (JsonProcessingException e) {
      throw new RedisBuildingException(
          "JsonProcessingException in getFiveMinData method");
    }
  }
}

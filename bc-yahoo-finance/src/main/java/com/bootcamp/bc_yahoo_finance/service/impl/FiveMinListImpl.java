package com.bootcamp.bc_yahoo_finance.service.impl;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bootcamp.bc_yahoo_finance.dto.reqDto.FiveMinListDTO;
import com.bootcamp.bc_yahoo_finance.dto.reqDto.YahooStockDTO;
import com.bootcamp.bc_yahoo_finance.entity.FiveMinListEntity;
import com.bootcamp.bc_yahoo_finance.entity.YahooStockEntity;
import com.bootcamp.bc_yahoo_finance.exception.RedisBuildingException;
import com.bootcamp.bc_yahoo_finance.infra.RedisHelper;
import com.bootcamp.bc_yahoo_finance.infra.TimeStampConverter;
import com.bootcamp.bc_yahoo_finance.mapper.YahooStockEntityMapper;
import com.bootcamp.bc_yahoo_finance.repository.YahooStockRepository;
import com.bootcamp.bc_yahoo_finance.service.FiveMinListService;
import com.bootcamp.bc_yahoo_finance.service.SystemDateService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FiveMinListImpl implements FiveMinListService{
  
  @Autowired 
  private RedisHelper redisHelper;

  @Autowired
  private YahooStockRepository yahooStockRepository;

  @Autowired
  private YahooStockEntityMapper yahooStockEntityMapper;

  @Autowired
  private SystemDateService systemDateService;

  @Override
  public FiveMinListDTO getFiveMinData(String symbol) throws RedisBuildingException{
log.info("-----------before try--------------");
    try {
      log.info("-------------------5555555555---------");
      String name = "5MIN-".concat(symbol);
      FiveMinListDTO fiveMinList = redisHelper.get(name, FiveMinListDTO.class);
      log.info("fiveMinList : "+fiveMinList);

      
        // produce systemdate
        systemDateService.getSystemDate(symbol);
        //construct the sysdate key
        String systemdateKey = "SYSDATE-".concat(symbol);
        // get the systemdate(yyyy-MM-dd) in Redis
        String systemdate = redisHelper.get(systemdateKey , String.class);
        // String -> LocalDate -> LocalDateTime -> Long of Timestamp Unix 
        LocalDate localDate = LocalDate.parse(systemdate);
        LocalDateTime localDateTime = localDate.atStartOfDay();
        Long date = localDateTime.toEpochSecond(ZoneOffset.UTC);
        Long dateMax = date + 86399;
        log.info("----------time--------" + dateMax);
        
        // get the max regularMarketUnix
        Long maxRegularMarketUnix = yahooStockRepository.findMaxMarketTimeBySymbol(symbol).orElseThrow();
        // get the 5min data in the date in systemdate
        List<YahooStockEntity> entityList = yahooStockRepository.findAllStockBySymbolByDate(symbol, date, dateMax).orElse(null);
        List<YahooStockDTO> dtoList = entityList.stream().map(e -> yahooStockEntityMapper.mapToYahooStockDTO(e)).collect(Collectors.toList());
        
        fiveMinList = FiveMinListDTO.builder().data(dtoList).regularMarketTIme(maxRegularMarketUnix).build();
        redisHelper.set("5MIN-".concat(symbol), fiveMinList, Duration.ofHours(12L));
        return fiveMinList;

    } catch (JsonProcessingException e) {
      throw new RedisBuildingException("JsonProcessingException in getFiveMinData method");
    }
  }
}

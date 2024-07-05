package com.bootcamp.bc_yahoo_finance.service.impl;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import com.bootcamp.bc_yahoo_finance.dto.respDto.ExYahooAPI;
import com.bootcamp.bc_yahoo_finance.dto.respDto.ExYahooHistory;
import com.bootcamp.bc_yahoo_finance.dto.respDto.ExYahooHistory.YahooChart.YahooResult;
import com.bootcamp.bc_yahoo_finance.dto.respDto.ExYahooAPI.QuoteResponse.ExYahooStock;
import com.bootcamp.bc_yahoo_finance.entity.YahooStockEntity;
import com.bootcamp.bc_yahoo_finance.exception.RedisBuildingException;
import com.bootcamp.bc_yahoo_finance.exception.StockDataNotAvailableException;
import com.bootcamp.bc_yahoo_finance.infra.ApiResp;
import com.bootcamp.bc_yahoo_finance.infra.CrumbManager;
import com.bootcamp.bc_yahoo_finance.infra.ErrorCode;
import com.bootcamp.bc_yahoo_finance.infra.RedisHelper;
import com.bootcamp.bc_yahoo_finance.infra.Scheme;
import com.bootcamp.bc_yahoo_finance.infra.SysCode;
import com.bootcamp.bc_yahoo_finance.infra.ApiResp.ApiRespBuilder;
import com.bootcamp.bc_yahoo_finance.mapper.ExYahooAPIMapper;
import com.bootcamp.bc_yahoo_finance.mapper.YahooStockHistoryMapper;
import com.bootcamp.bc_yahoo_finance.repository.YahooStockRepository;
import com.bootcamp.bc_yahoo_finance.service.StockSymbolService;
import com.bootcamp.bc_yahoo_finance.service.YahooHistoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class YahooHistoryServiceImpl implements YahooHistoryService {


  @Value(value = "${api.json-place-holder.domain}")
  private String domain;

  @Value(value = "${api.json-place-holder.endpoints.history}")
  private String historyEndpoint;

  @Value(value = "$.{header}")
  private String cookie;

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private YahooStockRepository yahooStockRepository;

  @Autowired
  private YahooStockHistoryMapper yahooStockHistoryMapper;

  @Autowired
  private ExYahooAPIMapper exYahooAPIMapper;

  @Autowired
  private RedisHelper redisHelper;

  @Override
  public ApiResp<ExYahooHistory> getYahooStockHistoryBySymbol(String symbol,
      String period1, String period2, String interval) {
    try {
      String url = UriComponentsBuilder.newInstance() //
          .scheme(Scheme.HTTPS.lowercase()) // https or http
          .host(this.domain) //
          .path(historyEndpoint) //
          .build(false)//
          .toUriString();

      String crumb = CrumbManager.getCrumb();
      String cookie = CrumbManager.getCookie();
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      headers.add("Cookie", cookie);
      HttpEntity<String> request = new HttpEntity<>(headers);

      String event = "history";

      log.info("url : " + url);
      UriComponents builder = UriComponentsBuilder.fromHttpUrl(url).path(symbol)//
          .queryParam("period1", period1).queryParam("period2", period2)
          .queryParam("interval", interval).queryParam("events", event)
          .queryParam("crumb", crumb).build(false)//
      ;
      log.info("builder : " + builder);
      ResponseEntity<ExYahooHistory> response = restTemplate.exchange(
          builder.toUriString(), HttpMethod.GET, request, ExYahooHistory.class);
      if (response.getStatusCode() == HttpStatus.OK) {
        return ApiResp.<ExYahooHistory>builder().ok()
            .data(List.of(response.getBody())).build();
      } else {
        return ApiResp.<ExYahooHistory>builder().error(ErrorCode.NPE).build();
      }
    } catch (HttpClientErrorException
        | HttpServerErrorException httpClientOrServerEx) {
      log.error(
          "Error during HTTP request: " + httpClientOrServerEx.getMessage());
      throw new StockDataNotAvailableException(
          "Failed to fetch stock data for symbol: " + symbol);
    } catch (RestClientException restClientEx) {
      log.error("RestClientException: " + restClientEx.getMessage());
      throw new StockDataNotAvailableException(
          "Failed to communicate with external API for symbol: " + symbol);
    } catch (Exception ex) {
      log.error("Unexpected error: " + ex.getMessage());
      throw new StockDataNotAvailableException(
          "Unexpected error occurred for symbol: " + symbol);
    }

  }


  @Override
  public ExYahooHistory getYahooStockHistoryBySymbol(String symbol,
      String interval) {
    return this
        .getYahooStockHistoryBySymbol(symbol, "1", "3000000000", interval)
        .getData().get(0);
  }

  @Override
  public ExYahooHistory getYahooStockHistoryBySymbol(String symbol) {

    return this.getYahooStockHistoryBySymbol(symbol, "1", "3000000000", "1d")
        .getData().get(0);
  }

  @Override
  public void saveYahooHistory(String symbol, List<ExYahooAPI> exYahooAPIs) {

    List<YahooStockEntity> yahooStockEntities =
        exYahooAPIs.stream().map(e -> exYahooAPIMapper.mapToYahooStockEntity(e))
            .collect(Collectors.toList());
    yahooStockRepository.saveAll(yahooStockEntities);
  }

  @Override
  public List<YahooStockEntity> getFromRedis(String key) {
    try {
      YahooStockEntity[] entities =
          redisHelper.get(key, YahooStockEntity[].class);
      if (entities == null) {
        return null;
      }
      return Arrays.asList(entities);
    } catch (JsonProcessingException e) {
      throw new RedisBuildingException("Failed to process JSON for key: " + key,
          e);
    }
  }

  @Override
  public void setFromRedis(List<YahooStockEntity> entities, String symbol) {
    try {
      redisHelper.set(symbol, entities, Duration.ofMinutes(10L));
    } catch (JsonProcessingException e) {
      throw new RedisBuildingException(
          "Failed to process JSON for key: " + symbol, e);
    }
  }

  @Override
  public ApiResp<YahooStockEntity> saveFromYahooAPI(String symbol,
      String interval, String start) {

    // nowTime
    LocalDate today = LocalDate.now();
    LocalDateTime todayMidnight = today.atStartOfDay();
    long nowTime = todayMidnight.toInstant(ZoneOffset.UTC).getEpochSecond();

    // get yahoo history
      ExYahooHistory exYahooHistory = this.getYahooStockHistoryBySymbol(symbol, start,
          String.valueOf(nowTime), interval).getData().get(0);
     
          // early return 
          if (exYahooHistory == null || exYahooHistory.getChart().getResult().get(0).getIndicators().getQuote().get(0).getOpen() == null) {
            return ApiResp.<YahooStockEntity>builder().error(SysCode.NOT_FOUND).build();
          }
    // map to yahooStockEntity
    List<YahooStockEntity> yahooStockEntities =
        yahooStockHistoryMapper.mapToStockEntity(exYahooHistory, interval);

        if (yahooStockEntities == null) {
          return ApiResp.<YahooStockEntity>builder().error(ErrorCode.NPE).build();
        }

    // save in database
    yahooStockRepository.saveAll(yahooStockEntities);

    // response success
    return ApiResp.<YahooStockEntity>builder().ok().build();

  }


  @Override
  public ApiResp<YahooStockEntity> getAndSaveYahooHistroy(String symbol,
      String interval) {
      // check redis whether has data
    String key = symbol.concat("-").concat(interval);
    List<YahooStockEntity> entities = this.getFromRedis(key);
    if (entities != null ) {
      return ApiResp.<YahooStockEntity>builder().ok().data(entities).build();
    }

    // get the last data from database
    Optional<Long> maxMarketTime =
        yahooStockRepository.findMaxMarketTimeBySymbolAndDataType(symbol,
            "history".concat(interval));
    // get nowTime
    LocalDate today = LocalDate.now();
    LocalDateTime todayMidnight = today.atStartOfDay();
    long nowTime = todayMidnight.toInstant(ZoneOffset.UTC).getEpochSecond();

    // main logic
    ExYahooHistory exYahooHistory = null;

    if (maxMarketTime.isPresent()) {
      //check any data between last time and now
      ApiResp<YahooStockEntity> response = saveFromYahooAPI(symbol, interval, String.valueOf(maxMarketTime.get()));
      if (response.getCode() == 99) {
        return ApiResp.<YahooStockEntity> builder().error(ErrorCode.NPE).build();
      }
      } else {
        // save all data from yahooAPI
        ApiResp<YahooStockEntity> response = saveFromYahooAPI(symbol, interval, "1");
        if (response.getCode() == 99) {
          return ApiResp.<YahooStockEntity> builder().error(ErrorCode.NPE).build();
        }
    }
    // get data from database
    List<YahooStockEntity> yahooStockEntities = yahooStockRepository
    .findAllStockBySymbolAndDataType(symbol, "history".concat(interval))
    .get();
    
    if (yahooStockEntities == null || yahooStockEntities.isEmpty()) {
      return ApiResp.<YahooStockEntity>builder().error(ErrorCode.NPE).build();
    }

     // save in redis 
    this.setFromRedis(yahooStockEntities, key);

    return ApiResp.<YahooStockEntity>builder().ok().data(yahooStockEntities)
        .build();

  }

  // @Override
  // public void saveYahooAPI(String symbol) {
  // ExYahooAPI stock = this.getYahooStockBySymbol(symbol);
  // YahooStockEntity stockEntity = exYahooAPIMapper.mapToYahooFinanceEntity(stock);
  // log.debug(stockEntity.getRegularMarketUnix().toString());
  // yahooStockRepository.save(stockEntity);
  // }

}

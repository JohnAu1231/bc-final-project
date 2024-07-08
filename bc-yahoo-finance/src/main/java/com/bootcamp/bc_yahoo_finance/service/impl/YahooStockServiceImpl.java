package com.bootcamp.bc_yahoo_finance.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import com.bootcamp.bc_yahoo_finance.dto.respDto.ExYahooAPI;
import com.bootcamp.bc_yahoo_finance.entity.YahooStockEntity;
import com.bootcamp.bc_yahoo_finance.infra.CrumbManager;
import com.bootcamp.bc_yahoo_finance.infra.Scheme;
import com.bootcamp.bc_yahoo_finance.mapper.ExYahooAPIMapper;
import com.bootcamp.bc_yahoo_finance.repository.YahooStockRepository;
import com.bootcamp.bc_yahoo_finance.service.YahooStockService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class YahooStockServiceImpl implements YahooStockService {

  @Value(value = "${api.json-place-holder.domain}")
  private String domain;

  @Value(value = "${api.json-place-holder.endpoints.stock}")
  private String stockEndpoint;

  @Value(value = "$.{header}")
  private String cookie;

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private YahooStockRepository yahooStockRepository;

  @Autowired
  private ExYahooAPIMapper exYahooAPIMapper;

  @Override
  public ExYahooAPI getYahooStockBySymbol(String symbol) {

    String url = UriComponentsBuilder.newInstance() //
        .scheme(Scheme.HTTPS.lowercase()) // https or http
        .host(this.domain) //
        .path(stockEndpoint) //
        .build(false)//
        .toUriString();

    String crumb = CrumbManager.getCrumb();
    String cookie = CrumbManager.getCookie();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.add("Cookie", cookie);
    HttpEntity<String> request = new HttpEntity<>(headers);

    log.info("url : " + url);
    UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
        .queryParam("symbols", symbol)//
        .queryParam("crumb", crumb)//
        .build(false)//
    ;
    log.info("builder : " + builder);
    ResponseEntity<ExYahooAPI> response = restTemplate.exchange(
        builder.toUriString(), HttpMethod.GET, request, ExYahooAPI.class);
    return response.getBody();
  }

  @Override
  public void saveYahooAPI(String symbol) {
    ExYahooAPI stock = this.getYahooStockBySymbol(symbol);
    YahooStockEntity stockEntity = exYahooAPIMapper.mapToYahooFinanceEntity(stock);
    stockEntity.setDataType("day");
    log.debug(stockEntity.getRegularMarketUnix().toString());
    yahooStockRepository.save(stockEntity);
  }

}

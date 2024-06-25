package com.bootcamp.bc_stock_web.service.impl;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.bootcamp.bc_stock_web.dto.reqdto.YahooStockDTO;
import com.bootcamp.bc_stock_web.dto.respdto.ExFiveMinList;
import com.bootcamp.bc_stock_web.infra.Scheme;
import com.bootcamp.bc_stock_web.service.MovingAverageService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MovingAverageServiceImpl implements MovingAverageService{
  
    @Value(value = "${api.json-place-holder.domain}")
  private String domain;

  @Value(value = "${api.json-place-holder.endpoints.hour}")
  private String endpoint;

  @Autowired
  private RestTemplate restTemplate;

    @Override
  public List<YahooStockDTO> getHourAveragePoint(String symbol) {
    String url = UriComponentsBuilder.newInstance().scheme(Scheme.HTTP.lowercase())
        .host(this.domain).path(endpoint)
        .path("/").path(symbol).build(false).toUriString();

    log.info("url : " + url);


    YahooStockDTO[] response =
        restTemplate.getForObject(url, YahooStockDTO[].class);
    return Arrays.asList(response);
  }
}

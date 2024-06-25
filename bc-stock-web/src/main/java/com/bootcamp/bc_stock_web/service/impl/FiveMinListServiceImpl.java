package com.bootcamp.bc_stock_web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import com.bootcamp.bc_stock_web.dto.respdto.ExFiveMinList;
import com.bootcamp.bc_stock_web.infra.Scheme;
import com.bootcamp.bc_stock_web.service.FiveMinListService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FiveMinListServiceImpl implements FiveMinListService {

  @Value(value = "${api.json-place-holder.domain}")
  private String domain;

  @Value(value = "${api.json-place-holder.endpoints.stock}")
  private String stockEndpoint;

  @Autowired
  private RestTemplate restTemplate;


  @Override
  public ExFiveMinList getFiveMinList(String symbol) {
    String url = UriComponentsBuilder.newInstance().scheme(Scheme.HTTP.lowercase())
        .host(this.domain).path(stockEndpoint)
        .path("/").path(symbol).build(false).toUriString();

    log.info("url : " + url);


    ExFiveMinList response =
        restTemplate.getForObject(url, ExFiveMinList.class);
    return response;
  }
}

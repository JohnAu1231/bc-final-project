  package com.bootcamp.bc_stock_web.service.impl;

  import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.bootcamp.bc_stock_web.dto.respdto.ExFiveMinList;
import com.bootcamp.bc_stock_web.infra.ApiResp;
import com.bootcamp.bc_stock_web.infra.ErrorCode;
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

    @Value(value = "${api.json-place-holder.endpoints.history}")
    private String historyEndpoint;

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public ExFiveMinList getFiveMinListAllTime(String symbol) {
      String url = UriComponentsBuilder.newInstance().scheme(Scheme.HTTP.lowercase())
          .host(this.domain).path(stockEndpoint)
          .path("/all")
          .path("/").path(symbol).build(false).toUriString();

      log.info("url : " + url);


      ExFiveMinList response =
          restTemplate.getForObject(url, ExFiveMinList.class);
      return response;
    }

    @Override
    public ApiResp<ExFiveMinList> getHistoryList(String symbol, String interval) {
      String url = UriComponentsBuilder.newInstance().scheme(Scheme.HTTP.lowercase())
          .host(this.domain).path(historyEndpoint)
          .path("/").path(symbol).path("/").path(interval).build(false).toUriString();

      log.info("url : " + url);

        
          ApiResp<ExFiveMinList> response = fetchData(url, ExFiveMinList.class);
        
          if (response.getCode() == 0) {
            List<ExFiveMinList> data = response.getData();
              
            return ApiResp.<ExFiveMinList>builder().ok().data(data).build();
  } 
      return ApiResp.<ExFiveMinList>builder().error(ErrorCode.NPE).build();

    };

  public <T> ApiResp<T> fetchData(String url, Class<T> responseType) {
    try {
        ResponseEntity<ApiResp<T>> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResp<T>>() {}
        );

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return responseEntity.getBody();
        } else {
            // Handle non-successful status code
            return ApiResp.<T>builder().code(666).message("api resp cannnot build").build();
        }
    } catch (RestClientException e) {
        // Handle RestClientException
        return ApiResp.<T>builder().code(666).message("restclient exception").build();
    }
}


}



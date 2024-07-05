package com.bootcamp.bc_stock_web.controller.impl;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.bc_stock_web.controller.StockPriceOperation;
import com.bootcamp.bc_stock_web.dto.reqdto.YahooStockDTO;
import com.bootcamp.bc_stock_web.dto.respdto.ExFiveMinList;
import com.bootcamp.bc_stock_web.infra.ApiResp;
import com.bootcamp.bc_stock_web.infra.ErrorCode;
import com.bootcamp.bc_stock_web.mapper.YahooStockDTOMapper;
import com.bootcamp.bc_stock_web.model.StockPrice;
import com.bootcamp.bc_stock_web.service.FiveMinListService;


@RestController
public class StockPriceController implements StockPriceOperation {

  @Autowired
  private FiveMinListService fiveMinListService;

  @Autowired
  private YahooStockDTOMapper yahooStockDTOMapper;

  public List<StockPrice> getFiveMinuteData(String symbol) {
    
    ExFiveMinList ls = fiveMinListService.getFiveMinList(symbol);
    return ls.getData().stream().map(e -> yahooStockDTOMapper.mapToStockPrice(e)).collect(Collectors.toList());

  }
  public List<YahooStockDTO> getFiveMinuteList(String symbol) {
  ExFiveMinList ls = fiveMinListService.getFiveMinList(symbol);
  return ls.getData();
  }

  @Override
   public ApiResp<ExFiveMinList> getHistoryStock(String symbol, String interval) {

        return fiveMinListService.getHistoryList(symbol, interval);

      //   if (apiResp.getCode() != 0) {
      //     return apiResp;
      // }

      // ExFiveMinList ls = apiResp.getData().get(0);
      // return ResponseEntity.ok(ls.getData());
   }

}
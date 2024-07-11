package com.bootcamp.bc_yahoo_finance.controller.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.bc_yahoo_finance.controller.StockOperation;
import com.bootcamp.bc_yahoo_finance.dto.reqDto.FiveMinListDTO;
import com.bootcamp.bc_yahoo_finance.dto.reqDto.YahooStockDTO;
import com.bootcamp.bc_yahoo_finance.dto.respDto.ExYahooAPI;
import com.bootcamp.bc_yahoo_finance.dto.respDto.ExYahooHistory;
import com.bootcamp.bc_yahoo_finance.entity.YahooStockEntity;
import com.bootcamp.bc_yahoo_finance.exception.RedisBuildingException;
import com.bootcamp.bc_yahoo_finance.infra.ApiResp;
import com.bootcamp.bc_yahoo_finance.mapper.YahooStockEntityMapper;
import com.bootcamp.bc_yahoo_finance.mapper.YahooStockHistoryMapper;
import com.bootcamp.bc_yahoo_finance.service.FiveMinListService;
import com.bootcamp.bc_yahoo_finance.service.SystemDateService;
import com.bootcamp.bc_yahoo_finance.service.YahooHistoryService;
import com.bootcamp.bc_yahoo_finance.service.YahooStockService;

@RestController
public class StockController implements StockOperation{
  
  @Autowired
  private YahooStockService yahooStockService;

  @Autowired
  private SystemDateService systemDateService;

  @Autowired
  private YahooStockEntityMapper yahooStockEntityMapper;

  @Autowired
  private FiveMinListService fiveMinListService;

  @Autowired
  private YahooHistoryService yahooHistoryService;
  
  @Autowired
  private YahooStockHistoryMapper yahooStockHistoryMapper;


  @Override
  public ExYahooAPI getYahooAPI(String symbol) {
    return yahooStockService.getYahooStockBySymbol(symbol);
  }


  @Override
  public String getSystemDate(String symbol) {
    return systemDateService.getSystemDate(symbol);
  }

  @Override
  public FiveMinListDTO getFiveMinListDTOToday(String symbol){
    return fiveMinListService.getFiveMinDataToday(symbol);
  }

  @Override
  public FiveMinListDTO getFiveMinListDTOAllTime(String symbol)  {
    return fiveMinListService.getFiveMinDataAllTime(symbol);
  }

  @Override
   public FiveMinListDTO getYahooStockHistoryBySymbol(String symbol) {
    ApiResp<ExYahooHistory> exYahooHistory = yahooHistoryService.getYahooStockHistoryBySymbol(symbol);
    return yahooStockHistoryMapper.mapToStockDayPriceDTO(exYahooHistory.getData().get(0));
   }

   @Override
   public ApiResp<FiveMinListDTO> getYahooStockHistoryBySymbol(String symbol, String interval) {
    ApiResp<YahooStockEntity> result = yahooHistoryService.getAndSaveYahooHistroy(symbol, interval);

    // return error code to frontend
    if (result.getCode() != 0) {
      return ApiResp.<FiveMinListDTO>builder().code(result.getCode()).message(result.getMessage()).build();
    }
    List<YahooStockEntity> yahooStockEntities = yahooHistoryService.getAndSaveYahooHistroy(symbol, interval).getData();
    List<YahooStockDTO> yahooStockDtos = yahooStockEntities.stream().map(e -> yahooStockEntityMapper.mapToYahooStockDTO(e)).collect(Collectors.toList());
    return ApiResp.<FiveMinListDTO>builder().ok().data(List.of(FiveMinListDTO.builder().data(yahooStockDtos).build())).build();
   }
}

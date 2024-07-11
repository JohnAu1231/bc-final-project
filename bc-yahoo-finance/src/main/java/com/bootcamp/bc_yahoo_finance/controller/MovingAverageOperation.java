package com.bootcamp.bc_yahoo_finance.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.bootcamp.bc_yahoo_finance.dto.reqDto.MovingAverageDTO;
import com.bootcamp.bc_yahoo_finance.dto.reqDto.YahooStockDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public interface MovingAverageOperation {
  
  @Operation(summary = "GET Moving Average per hour by symbol",
    description = "",
    parameters = {@Parameter(name = "symbol", description = "Stock id",
    required = true, in = ParameterIn.PATH)})
   @ApiResponses({
      @ApiResponse(responseCode = "200",
          content = {@Content(schema = @Schema(implementation = YahooStockDTO.class),
              mediaType = "application/json")}),
      @ApiResponse(responseCode = "500",
          content = {@Content(schema = @Schema())})})
  @GetMapping(value = "/hourMA/{symbol}/{period}")
  @ResponseStatus(value = HttpStatus.OK)
   List<YahooStockDTO> getHourMovingAvergae(@PathVariable String symbol, @PathVariable int period);

   
  @GetMapping(value = "/dayMA/{symbol}/{period}")
  List<MovingAverageDTO> getHourMovingAvergaeByDay(@PathVariable String symbol, @PathVariable int period);
}

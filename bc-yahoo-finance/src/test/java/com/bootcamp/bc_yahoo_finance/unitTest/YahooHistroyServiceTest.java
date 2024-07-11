package com.bootcamp.bc_yahoo_finance.unitTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.bootcamp.bc_yahoo_finance.dto.respDto.ExYahooHistory;
import com.bootcamp.bc_yahoo_finance.exception.StockDataNotAvailableException;
import com.bootcamp.bc_yahoo_finance.infra.ApiResp;
import com.bootcamp.bc_yahoo_finance.service.YahooHistoryService;

@SpringBootTest
public class YahooHistroyServiceTest {

    @Autowired
    private YahooHistoryService yahooHistoryService;

    @Test
    void testYahooAPI() {
        // normal get
        ApiResp<ExYahooHistory> response = yahooHistoryService.getYahooStockHistoryBySymbol("0388.HK");
        MatcherAssert.assertThat(response.getCode(), Matchers.is(0));
        MatcherAssert.assertThat(response.getData().isEmpty(), Matchers.is(false));

        // if yahoo doesn't have symbol
        StockDataNotAvailableException exception = assertThrows(StockDataNotAvailableException.class, () -> {
            yahooHistoryService.getYahooStockHistoryBySymbol("98765");});
        MatcherAssert.assertThat(exception.getMessage(), Matchers.containsString("Failed to fetch stock data for symbol: 98765"));
    }
}

package com.bootcamp.bc_yahoo_finance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.bootcamp.bc_yahoo_finance.service.YahooHistoryService;
import com.fasterxml.jackson.core.JsonProcessingException;

@SpringBootTest
public class YahooHistroyServiceTest {

    @Autowired
    private YahooHistoryService yahooHistoryService;

    // @Test
    // void givenWithoutPlusSign_whenGet_thenSameValueReturned() throws JsonProcessingException {

    // }
}

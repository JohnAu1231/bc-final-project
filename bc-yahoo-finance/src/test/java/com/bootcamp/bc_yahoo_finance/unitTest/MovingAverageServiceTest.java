package com.bootcamp.bc_yahoo_finance.unitTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.bootcamp.bc_yahoo_finance.entity.YahooStockEntity;
import com.bootcamp.bc_yahoo_finance.repository.YahooStockRepository;
import com.bootcamp.bc_yahoo_finance.service.MovingAverageService;

@WebMvcTest(MovingAverageService.class)
class MovingAverageServiceTest {

  @Autowired
  private MovingAverageService movingAverageService;

  @MockBean
  private YahooStockRepository yahooStockRepository;

  @Test
  void testGetMovingAverageByDay() {

    // test the method if period = list.size()
    List<YahooStockEntity> entityList = new ArrayList<>();
    YahooStockEntity yahooEntity1 =
        YahooStockEntity.builder().regularMarketUnix(10000L).close(10d).build();
    YahooStockEntity yahooEntity2 =
        YahooStockEntity.builder().regularMarketUnix(11000L).close(20d).build();
    YahooStockEntity yahooEntity3 =
        YahooStockEntity.builder().regularMarketUnix(12000L).close(30d).build();
    YahooStockEntity yahooEntity4 =
        YahooStockEntity.builder().regularMarketUnix(13000L).close(40d).build();
    YahooStockEntity yahooEntity5 =
        YahooStockEntity.builder().regularMarketUnix(14000L).close(50d).build();
    entityList.add(yahooEntity1);
    entityList.add(yahooEntity2);
    entityList.add(yahooEntity3);
    entityList.add(yahooEntity4);
    entityList.add(yahooEntity5);
    Mockito.when(yahooStockRepository.findAllStockBySymbolAndDataType("0001",
        "history1d")).thenReturn(Optional.of(entityList));
    MatcherAssert.assertThat(movingAverageService
        .getMovingAverageByDay("0001", 5).get(0).getRegularMarketPrice(),
        Matchers.is(10d));
    MatcherAssert.assertThat(movingAverageService
        .getMovingAverageByDay("0001", 5).get(1).getRegularMarketPrice(),
        Matchers.is(15d));
    MatcherAssert.assertThat(movingAverageService
        .getMovingAverageByDay("0001", 5).get(2).getRegularMarketPrice(),
        Matchers.is(20d));
    MatcherAssert.assertThat(movingAverageService
        .getMovingAverageByDay("0001", 5).get(3).getRegularMarketPrice(),
        Matchers.is(25d));
    MatcherAssert.assertThat(movingAverageService
        .getMovingAverageByDay("0001", 5).get(4).getRegularMarketPrice(),
        Matchers.is(30d));

    // test the method if period < list.size()
    List<YahooStockEntity> entityList2 = new ArrayList<>();
    entityList2.add(yahooEntity2);
    entityList2.add(yahooEntity2);
    entityList2.add(yahooEntity2);
    entityList2.add(yahooEntity2);
    entityList2.add(yahooEntity2);
    entityList2.add(yahooEntity2);
    entityList2.add(yahooEntity2);
    Mockito.when(yahooStockRepository.findAllStockBySymbolAndDataType("0001",
        "history1d")).thenReturn(Optional.of(entityList2));
    MatcherAssert.assertThat(movingAverageService
        .getMovingAverageByDay("0001", 5).get(0).getRegularMarketPrice(),
        Matchers.is(20d));
    MatcherAssert.assertThat(movingAverageService
        .getMovingAverageByDay("0001", 5).get(1).getRegularMarketPrice(),
        Matchers.is(20d));
    MatcherAssert.assertThat(movingAverageService
        .getMovingAverageByDay("0001", 5).get(2).getRegularMarketPrice(),
        Matchers.is(20d));
    MatcherAssert.assertThat(movingAverageService
        .getMovingAverageByDay("0001", 5).get(3).getRegularMarketPrice(),
        Matchers.is(20d));
    MatcherAssert.assertThat(movingAverageService
        .getMovingAverageByDay("0001", 5).get(4).getRegularMarketPrice(),
        Matchers.is(20d));
    MatcherAssert.assertThat(movingAverageService
        .getMovingAverageByDay("0001", 5).get(5).getRegularMarketPrice(),
        Matchers.is(20d));
    MatcherAssert.assertThat(movingAverageService
        .getMovingAverageByDay("0001", 5).get(6).getRegularMarketPrice(),
        Matchers.is(20d));

    // test the method if period < list.size()
    List<YahooStockEntity> entityList3 = new ArrayList<>();
    entityList3.add(yahooEntity3);
    entityList3.add(yahooEntity2);
    entityList3.add(yahooEntity1);
    Mockito.when(yahooStockRepository.findAllStockBySymbolAndDataType("0001",
        "history1d")).thenReturn(Optional.of(entityList3));
    MatcherAssert.assertThat(movingAverageService
        .getMovingAverageByDay("0001", 5).get(0).getRegularMarketPrice(),
        Matchers.is(30d));
    MatcherAssert.assertThat(movingAverageService
        .getMovingAverageByDay("0001", 5).get(1).getRegularMarketPrice(),
        Matchers.is(25d));
    MatcherAssert.assertThat(movingAverageService
        .getMovingAverageByDay("0001", 5).get(2).getRegularMarketPrice(),
        Matchers.is(20d));

  }
}

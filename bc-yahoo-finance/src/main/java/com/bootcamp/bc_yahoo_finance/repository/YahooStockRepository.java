package com.bootcamp.bc_yahoo_finance.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.bootcamp.bc_yahoo_finance.dto.reqDto.YahooStockDTO;
import com.bootcamp.bc_yahoo_finance.entity.YahooStockEntity;

@Repository
public interface YahooStockRepository extends JpaRepository<YahooStockEntity, Long>{
  
@Query(value = "SELECT MAX(market_time) FROM TSTOCK_QUOTE_YAHOO WHERE symbol = :symbol", nativeQuery = true)
Optional<Long> findMaxMarketTimeBySymbol(@Param("symbol") String symbol); 


@Query(value = "SELECT * FROM TSTOCK_QUOTE_YAHOO WHERE symbol = :symbol ORDER BY market_time DESC", nativeQuery = true)
Optional<YahooStockEntity> findAllStockBySymbolByRecent(@Param("symbol") String symbol);

@Query(value = "SELECT * FROM TSTOCK_QUOTE_YAHOO WHERE symbol = :symbol AND market_time BETWEEN :marketTime AND :marketTimeMax ORDER BY market_time ASC", nativeQuery = true)
Optional<List<YahooStockEntity>> findAllStockBySymbolByDate(@Param("symbol") String symbol, @Param("marketTime") Long marketTime, @Param("marketTimeMax") Long max);

@Query(value = "SELECT * FROM TSTOCK_QUOTE_YAHOO WHERE symbol = :symbol AND market_time < :marketTime ORDER BY market_time DESC LIMIT 12", nativeQuery = true)
Optional<List<YahooStockEntity>> findAllStockBySymbolByNearlyHour(@Param("symbol") String symbol, @Param("marketTime") Long marketTime);

@Query(value = "SELECT * FROM TSTOCK_QUOTE_YAHOO WHERE symbol = :symbol ORDER BY market_time ASC", nativeQuery = true)
Optional<List<YahooStockEntity>> findAllStockBySymbol(@Param("symbol") String symbol);


}
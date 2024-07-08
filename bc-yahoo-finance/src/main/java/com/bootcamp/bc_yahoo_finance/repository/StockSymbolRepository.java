package com.bootcamp.bc_yahoo_finance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bootcamp.bc_yahoo_finance.entity.StockSymbolEntity;

@Repository
public interface StockSymbolRepository extends JpaRepository<StockSymbolEntity, Long>{
  
}

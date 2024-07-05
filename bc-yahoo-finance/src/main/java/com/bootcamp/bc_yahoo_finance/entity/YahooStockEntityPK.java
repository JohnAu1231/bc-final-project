package com.bootcamp.bc_yahoo_finance.entity;

import java.io.Serializable;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class YahooStockEntityPK implements Serializable {

    private Long regularMarketUnix;
    private String symbol;
    private String dataType;

    // Implementing Serializable interface
    // equals and hashCode methods

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        YahooStockEntityPK that = (YahooStockEntityPK) o;
        return Objects.equals(regularMarketUnix, that.regularMarketUnix) &&
                Objects.equals(symbol, that.symbol) &&
                Objects.equals(dataType, that.dataType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(regularMarketUnix, symbol, dataType);
    }
}

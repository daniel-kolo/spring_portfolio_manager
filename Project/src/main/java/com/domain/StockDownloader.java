package com.domain;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;

public class StockDownloader {

    public BigDecimal getStockPrice(String stockName) throws IOException {

        return YahooFinance.get(stockName).getQuote().getPrice();
    }

}

package com.DTO;

import com.domain.Stock;
import com.domain.StockPortfolio;
import com.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StockPortfolioDTO {
    @JsonIgnore
    private User user ;
    private List<Stock> stocks;
    private Map<String, Integer> stockMap;

    public StockPortfolioDTO(StockPortfolio portfolio){
        user = portfolio.getUser();
        stocks = portfolio.getStocks();
        stockMap = portfolio.getStockMap();
    }

    public User getUser() {
        return user;
    }

    public List<Stock> getStocks() {
        return stocks;
    }

    public Map<String, Integer> getStockMap() {
        return stockMap;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setStocks(List<Stock> stocks) {
        this.stocks = stocks;
    }

    public void setStockMap(Map<String, Integer> stockMap) {
        this.stockMap = stockMap;
    }
}

package com.viewmodel;

import com.domain.Stock;
import com.domain.User;

import java.util.HashMap;

public class MainSiteModel {

    private User user;
    private Stock[] randomStocks;
    private HashMap<String, StockInfoView> stocksInfo;

    public MainSiteModel(Stock[] currentShown, HashMap<String, StockInfoView> stockInfo) {
        this.user = null;
        this.randomStocks = currentShown;
        this.stocksInfo = stockInfo;
    }

    public MainSiteModel() {
    }

    public MainSiteModel(User user, Stock[] currentShown) {
        this.user = user;
        this.randomStocks = currentShown;
    }

    public User getUser() {
        return user;
    }

    public Stock[] getRandomStocks() {
        return randomStocks;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setRandomStocks(Stock[] randomStocks) {
        this.randomStocks = randomStocks;
    }

    public HashMap<String, StockInfoView> getStocksInfo() {
        return stocksInfo;
    }

    public void setStocksInfo(HashMap<String, StockInfoView> stocksInfo) {
        this.stocksInfo = stocksInfo;
    }


}

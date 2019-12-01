package com.viewmodel;

import com.DTO.StockPortfolioDTO;
import com.DTO.UserDTO;
import com.domain.Stock;
import com.domain.User;

import java.util.HashMap;

public class MainSiteModel {

    private UserDTO user;
    private StockPortfolioDTO portfolioDTO;
    private Stock[] randomStocks;
    private HashMap<String, StockInfoView> stocksInfo;

    public MainSiteModel(Stock[] currentShown, HashMap<String, StockInfoView> stockInfo) {
        this.user = null;
        this.portfolioDTO = null;
        this.randomStocks = currentShown;
        this.stocksInfo = stockInfo;
    }

    public MainSiteModel() {
    }

    public MainSiteModel(UserDTO user, Stock[] currentShown) {
        this.user = user;
        this.randomStocks = currentShown;
    }

    public UserDTO getUser() {
        return user;
    }

    public Stock[] getRandomStocks() {
        return randomStocks;
    }

    public void setUser(UserDTO user) {
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

    public StockPortfolioDTO getPortfolioDTO() {
        return portfolioDTO;
    }

    public void setPortfolioDTO(StockPortfolioDTO portfolioDTO) {
        this.portfolioDTO = portfolioDTO;
    }

    public void reset() {
        user = null;
        portfolioDTO = null;
    }
}

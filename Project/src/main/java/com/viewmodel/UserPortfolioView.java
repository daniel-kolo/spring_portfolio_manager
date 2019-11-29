package com.viewmodel;

import java.util.HashMap;

public class UserPortfolioView {

    private HashMap<String, StockInfoView> userPortfolio;

    public HashMap<String, StockInfoView> getUserPortfolio() {
        return userPortfolio;
    }

    public UserPortfolioView(StockInfoView[] portfolioData) {
        this.portfolioData = portfolioData;
        userPortfolio = new HashMap<String, StockInfoView>();
        for (StockInfoView v: portfolioData) {
            userPortfolio.put(v.getStockName(), v);
        }
    }

    private StockInfoView[] portfolioData;
}

package com.viewmodel;

import java.util.HashMap;

public class UserPortfolioView {

    private HashMap<String, StockInfoView> userPortfolio;
    private StockInfoView[] portfolioData;

    public HashMap<String, StockInfoView> getUserPortfolio() {
        return userPortfolio;
    }

    public HashMap<String, StockPartInfoView> getEditorview()
    {
        HashMap<String, StockPartInfoView> editorView = new HashMap<String, StockPartInfoView>();
        for (int i = 0; i < portfolioData.length; i++)
        {
            int lastIdx = portfolioData[i].getElementsToShow().length - 1;
            editorView.put(portfolioData[i].getStockName(), portfolioData[i].getElementsToShow()[lastIdx]);
        }
        return editorView;
    }

    public UserPortfolioView(StockInfoView[] portfolioData) {
        this.portfolioData = portfolioData;
        userPortfolio = new HashMap<String, StockInfoView>();
        for (StockInfoView v: portfolioData) {
            userPortfolio.put(v.getStockName(), v);
        }
    }
}

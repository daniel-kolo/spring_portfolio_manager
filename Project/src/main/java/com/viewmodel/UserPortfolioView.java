package com.viewmodel;

import java.util.HashMap;

public class UserPortfolioView {

    private HashMap<String, StockInfoView> userPortfolio;
    private StockInfoView[] portfolioData;

    public HashMap<String, StockInfoView> getUserPortfolio() {
        return userPortfolio;
    }

    public HashMap<String, EditableStockView> getEditorview()
    {
        HashMap<String, EditableStockView> editorView = new HashMap<>();
        for (int i = 0; i < portfolioData.length; i++)
        {
            int lastIdx = portfolioData[i].getElementsToShow().length - 1;
            StockPartInfoView last = portfolioData[i].getElementsToShow()[lastIdx];
            EditableStockView esv = new EditableStockView(
                    portfolioData[i].getStockName(),
                    portfolioData[i].getTickerId(),
                    last.getAmount(),
                    last.getPrice()
            );
            editorView.put(portfolioData[i].getStockName(), esv);
            System.out.println("Suer portfolio class, sotckname: " + portfolioData[i].getStockName());
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

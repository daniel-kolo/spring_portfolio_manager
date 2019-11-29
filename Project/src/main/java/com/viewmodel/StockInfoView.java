package com.viewmodel;

public class StockInfoView {

    public StockPartInfoView[] getElementsToShow() {
        return elementsToShow;
    }

    public String getStockName() {
        return stockName;
    }

    private String stockName;
    private StockPartInfoView[] elementsToShow;

    // TODO: Create a limit to show for example the last 10 only ?
    // TODO: get the min and max date values for the chart ?
    public StockInfoView(String stockName, StockPartInfoView[] elementsToShow) {
        this.stockName = stockName;
        this.elementsToShow = elementsToShow;
    }

}

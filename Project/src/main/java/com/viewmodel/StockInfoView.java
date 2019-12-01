package com.viewmodel;

public class StockInfoView {

    public StockPartInfoView[] getElementsToShow() {
        return elementsToShow;
    }

    public String getStockName() {
        return stockName;
    }

    public String getTickerId() { return tickerId; }

    public  Double getLastPrice() {return lastPrice;}

    public Integer getAmount() {
        return amount;
    }

    private String tickerId;
    private String stockName;
    private StockPartInfoView[] elementsToShow;
    private Double lastPrice;



    private Integer amount;

    // TODO: Create a limit to show for example the last 10 only ?
    // TODO: get the min and max date values for the chart ?
    public StockInfoView(String stockName, StockPartInfoView[] elementsToShow, String tickerId, Integer amount) {
        this.stockName = stockName;
        this.elementsToShow = elementsToShow;
        this.tickerId = tickerId;
        this.lastPrice = elementsToShow[elementsToShow.length - 1].getPrice();
        this.amount = amount;
    }

}

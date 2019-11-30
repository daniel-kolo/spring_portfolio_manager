package com.viewmodel;

import java.util.Date;

public class StockPartInfoView {

    public Date getDateDownload() {
        return dateDownload;
    }

    public void setDateDownload(Date dateDownload) {
        this.dateDownload = dateDownload;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    private Date dateDownload;
    private double price;
    private int amount;

    public StockPartInfoView() {
    }

    public StockPartInfoView(Date dateDownload, double price, int amount) {
        this.dateDownload = dateDownload;
        this.price = price;
        this.amount = amount;
    }
}

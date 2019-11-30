package com.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


@Entity
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(name = "stock_name")
    private String name;

    @Column(name = "code")
    private String ticker;

    @Column(name = "price")
    private double price;

    @Column(name = "acquire_date")
    private Date acquireDate;

    @JsonIgnore
    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name = "portfolio_id")
    private StockPortfolio portfolio;

    public Stock(String ticker, double price, Date date) {
        this.ticker = ticker;
        this.price = price;
        this.acquireDate = date;
    }

    public Stock(){}

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public StockPortfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(StockPortfolio portfolio) {
        this.portfolio = portfolio;
    }

    public String getTicker() {
        return ticker;
    }

    public double getPrice() {
        return price;
    }

    public Date getDate() {
        return acquireDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.ticker = code;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDate(Date date) {
        this.acquireDate = date;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", ticker='" + ticker + '\'' +
                ", price=" + price +
                ", date=" + acquireDate +
                '}';
    }
}

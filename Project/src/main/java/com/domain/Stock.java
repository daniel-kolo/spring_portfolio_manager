package com.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.persistence.*;
import java.util.Date;


@Entity
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(name = "stock_name", nullable = false)
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "number")
    private int number;

    @Column(name = "price")
    private double price;

    @Column(name = "date")
    private Date date;

    @JsonIgnore
    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name = "portfolio_id")
    private StockPortfolio portfolio;

    public Stock(String name, String code, double price, Date date) {
        this.name = name;
        this.code = code;
        this.price = price;
        this.date = date;
        this.number=1;
    }

    public Stock(){}

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public void addStock(){
        this.number+=1;
    }

    public void addStock(int num){
        this.number+=num;
    }

    public StockPortfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(StockPortfolio portfolio) {
        this.portfolio = portfolio;
    }

    public String getCode() {
        return code;
    }

    public int getNumber() {
        return number;
    }

    public double getPrice() {
        return price;
    }

    public Date getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", number=" + number +
                ", price=" + price +
                ", date=" + date +
                '}';
    }
}

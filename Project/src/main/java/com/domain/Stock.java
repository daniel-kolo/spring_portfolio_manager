package com.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "stocks")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private String name;
    private String code;
    private int number;
    private double price;
    private Date date;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "portfolio_id")
    private StockPortfolio portfolio;

    public Stock(String name, String code, double price, Date date) {
        this.name = name;
        this.code = code;
        this.price = price;
        this.date = date;
        this.number=1;
    }

    private Stock(){}

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

    @Override
    public String toString() {
        return "Stock{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", number=" + number +
                ", price=" + price +
                ", date=" + date +
                ", portfolio=" + portfolio +
                '}';
    }
}

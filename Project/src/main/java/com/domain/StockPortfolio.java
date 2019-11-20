package com.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Entity
public class StockPortfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "id")
    private int Id;

    @JsonIgnore
    @OneToOne
    @MapsId
    private User user ;

    @Column
    @OneToMany(targetEntity = Stock.class, mappedBy = "portfolio", cascade=CascadeType.ALL)
    private List<Stock> stocks= new ArrayList<>(0);;

    public StockPortfolio() {}

    public int getId() {
        return Id;
    }

    public List<Stock> getStocks() {
        return stocks;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setStocks(List<Stock> stocks) {
        this.stocks = stocks;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void addStock(Stock stock){
        stocks.add(stock);
    }

    @Override
    public String toString() {
        return "StockPortfolio{" +
                "Id=" + Id +
                ", stocks=" + stocks +
                '}';
    }
}

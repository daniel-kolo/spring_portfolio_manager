package com.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.repo.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;


@Entity
public class StockPortfolio {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int Id;

    @JsonIgnore
    @OneToOne
    @MapsId
    private User user ;


    @Column
    @OneToMany(
            mappedBy = "portfolio",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.EAGER
    )
    private List<Stock> stocks;

    @ElementCollection()
    private Map<String, Integer> stockMap;

    public StockPortfolio() {}

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public List<Stock> getStocks() {
        return stocks;
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

    public void addStock(String ticker, Integer num){

        if (stocks == null){
            stocks = new ArrayList<>(0);
        }

        if( stockMap == null)
        {
            stockMap =  new HashMap<>();
        }

        Date date = new Date();
        for (int i = 0; i<num; i++){
            StockDownloader downloader = BeanUtil.getBean(StockDownloader.class);

            // Maybe?
            //StockPriceService priceService = new StockPriceService();
            //double price =  priceService.getStockPrice(ticker);

            double price = downloader.getStockPriceByTicker(ticker).doubleValue();
            Stock newStock = new Stock(ticker, price, date);
            newStock.setPortfolio(this);
            stocks.add(newStock);

            user.addTransaction(date, ticker, price, true);

        }

        if (stockMap.containsKey(ticker)){
            stockMap.put(ticker, stockMap.get(ticker)+num);
        }
        else{
            stockMap.put(ticker, num);
        }

    }

    public Map<String, Integer> getStockMap() {
        return stockMap;
    }

    @Override
    public String toString() {
        return "StockPortfolio{" +
                "stocks=" + stocks +
                ", stockMap=" + stockMap +
                '}';
    }
}

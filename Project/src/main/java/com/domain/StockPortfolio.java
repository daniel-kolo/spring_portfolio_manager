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
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch=FetchType.EAGER
    )
    private List<Stock> stocks= new ArrayList<>(0);

    @ElementCollection
    private Map<String, Integer> stockMap = new HashMap<>();

    @Column
    private String test;

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

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

    public void addStock(String ticker, Integer num, StockRepository stockRepo){

        Date date = new Date();
        for (int i = 0; i<num; i++){
            StockDownloader downloader = BeanUtil.getBean(StockDownloader.class);

            // Maybe?
            //StockPriceService priceService = new StockPriceService();
            //double price =  priceService.getStockPrice(ticker);

            double price = downloader.getStockPriceByTicker(ticker).doubleValue();
            Stock newStock = new Stock(ticker, price, date);
            stocks.add(newStock);

            user.addTransaction(new Transaction(date, ticker, price, user, true));
            stockRepo.save(newStock);
        }

        if (stockMap.containsKey(ticker)){
            stockMap.put(ticker, stockMap.get(ticker)+num);
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

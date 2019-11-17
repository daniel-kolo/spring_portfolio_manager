package com.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Entity
@Table(name = "portfolios")
public class StockPortfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "id")
    private int Id;

    // previous solution, testing another one
    /*
    @OneToOne(mappedBy = "portfolio", cascade=CascadeType.ALL)
    private User user;
    */

    @OneToOne
    @MapsId
    private User user;

    @Column(name = "stocks")
    @OneToMany(mappedBy = "portfolio", cascade=CascadeType.ALL)
    private List<Stock> stocks = new ArrayList<>();


    public StockPortfolio() {}

    public void addStock(Stock stock){
        this.stocks.add(stock);
        stock.setPortfolio(this);
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public List<Stock> getStocks(){
        return stocks;
    }

}

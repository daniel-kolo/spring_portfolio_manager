package com.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.HashMap;

@Entity
public class StockPortfolio {

    @Id
    private int UserId;

    HashMap<String, Integer> stockMap = new HashMap<String, Integer>();
    //ArrayList<StockInstance>

    public StockPortfolio(int userId) {
        UserId = userId;
    }

    public void addStock(String stockName, Integer num ){
        this.stockMap.put(stockName, num);
    }

    public void addStock(String stockName ){
        this.stockMap.put(stockName, 1);
    }


}

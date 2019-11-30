package com.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private Date date;
    private String StockName;
    private float price;

    @JsonIgnore
    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Transaction(Date date, String stockName, float price, User user) {
        this.date = date;
        StockName = stockName;
        this.price = price;
        this.user = user;
    }
}

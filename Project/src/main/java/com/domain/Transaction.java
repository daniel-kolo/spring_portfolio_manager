package com.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private Date date;
    private String ticker;
    private double price;
    private Boolean bought;

    @JsonIgnore
    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Transaction(){}

    public Transaction(Date date, String ticker, double price, User user, Boolean bought) {
        this.date = date;
        ticker = ticker;
        this.price = price;
        this.user = user;
        this.bought = bought;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "date=" + date +
                ", ticker='" + ticker + '\'' +
                ", price=" + price +
                ", bought=" + bought +
                '}';
    }
}

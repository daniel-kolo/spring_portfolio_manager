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

    @Column
    private Date date;

    @Column
    private String ticker;

    @Column
    private double price;

    @Column
    private Boolean bought;

    @JsonIgnore
    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Transaction(){}

    public Transaction(Date date, String ticker, double price, User user, Boolean bought) {
        this.date = date;
        this.ticker = ticker;
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

package com.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class TransactionContainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int Id;

    @JsonIgnore
    @OneToOne
    @MapsId
    private User user ;

    @Column
    @OneToMany(
            mappedBy = "container",
            cascade = CascadeType.PERSIST
    )
    private List<Transaction> transactions;

    public TransactionContainer(){}

    public User getUser() {
        return user;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void addTransaction(Date date, String ticker, double price,  Boolean bought){
        if (transactions == null){
            transactions = new ArrayList<>();
        }

        Transaction transaction = new Transaction(date, ticker, price, bought);
        transaction.setContainer(this);
        transactions.add(transaction);

    }

    @Override
    public String toString() {
        return "TransactionContainer{" +

                ", transactions=" + transactions +
                '}';
    }
}

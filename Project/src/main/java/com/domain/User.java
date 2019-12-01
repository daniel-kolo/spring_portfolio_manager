package com.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.repo.StockRepository;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int Id;

    @Column(name= "user_name")
    private String name;

    @Column
    private String username;

    @Column
    @JsonIgnore
    private String password;

    @Column
    private String email;

    @OneToOne(
    mappedBy = "user",
    cascade = CascadeType.PERSIST,
    orphanRemoval = true,
    fetch = FetchType.EAGER
    )
    private  StockPortfolio portfolio;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL
    )
    private TransactionContainer transactionContainer ;

     public User() {} ;

    public StockPortfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(StockPortfolio portfolio) {
        if (portfolio == null) {
            if (this.portfolio != null) {
                this.portfolio.setUser(null);
            }
        }
        else {
            portfolio.setUser(this);
        }
        this.portfolio = portfolio;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void addTransaction(Date date, String  ticker, Double price,Boolean bought){
        if (transactionContainer == null){
            transactionContainer = new TransactionContainer();
            transactionContainer.setUser(this);
        }

        transactionContainer.addTransaction(date, ticker, price,  bought);
    }

    public TransactionContainer getTransactionContainer(){
        return transactionContainer;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", portfolio=" + portfolio +
                ", transactionContainer=" + transactionContainer +
                '}';
    }
}

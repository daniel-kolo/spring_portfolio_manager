package com.DTO;

import com.domain.StockPortfolio;
import com.domain.Transaction;
import com.domain.User;

import java.util.List;

public class UserDTO {
    private String name;
    private String username;
    private String password;
    private String email;
    private StockPortfolio portfolio;
    private List<Transaction> transactionList;

    public UserDTO(User user){
        name = user.getName();
        username = user.getUsername();
        password = user.getPassword();
        email = user.getEmail();
        portfolio = user.getPortfolio();
        transactionList = user.getTransactionList();
    }

    public UserDTO(){}

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public StockPortfolio getPortfolio() {
        return portfolio;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPortfolio(StockPortfolio portfolio) {
        this.portfolio = portfolio;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", portfolio=" + portfolio +
                ", transactionList=" + transactionList +
                '}';
    }
}
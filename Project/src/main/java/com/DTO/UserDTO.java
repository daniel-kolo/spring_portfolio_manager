package com.DTO;

import com.domain.StockPortfolio;
import com.domain.Transaction;
import com.domain.TransactionContainer;
import com.domain.User;

import java.util.List;

public class UserDTO {
    private String name;
    private String username;
    private String password;
    private String email;
    private StockPortfolio portfolio;
    private TransactionContainer transactionContainer;

    public UserDTO(User user){
        name = user.getName();
        username = user.getUsername();
        password = user.getPassword();
        email = user.getEmail();
        portfolio = user.getPortfolio();
        transactionContainer = user.getTransactionContainer();
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

    public TransactionContainer getTransactionContainer() {
        return transactionContainer;
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

    public void setTransactionContainer(TransactionContainer transactionContainer) {
        this.transactionContainer = transactionContainer;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", portfolio=" + portfolio +
                ", transactionContainer=" + transactionContainer +
                '}';
    }
}
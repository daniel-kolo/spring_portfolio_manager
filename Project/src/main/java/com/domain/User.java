package com.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int Id;

    @Column(name= "user_name")
    private String name;


    @OneToOne(
    mappedBy = "user",
    cascade = CascadeType.ALL,
    orphanRemoval = true,
    fetch = FetchType.LAZY
    )
    private  StockPortfolio portfolio;

    /*
    @OneToOne(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private  Report report;
    */

    public User( String name) {
        this.name = name;
    }

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


}

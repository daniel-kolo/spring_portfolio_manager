package com.domain;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "id")
    private int Id;

    @Column(name= "user_name")
    private String name;

    // previous solution, testing another one
    /*
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "portfolio_id", referencedColumnName = "id")
    private StockPortfolio portfolio;

     */

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private StockPortfolio portfolio;

    public User( String name) {
        this.name = name;
    }

    protected User() {} ;

    public StockPortfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(StockPortfolio portfolio) {
        this.portfolio = portfolio;
        portfolio.setUser(this);
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


    @Override
    public String toString() {
        return "User{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                '}';
    }
}

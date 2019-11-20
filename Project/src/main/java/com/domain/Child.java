package com.domain;

import javax.persistence.*;

@Entity
public class Child {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int Id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Parent parent = new Parent();

    public Child() {}

    public void addParent(Parent parent){
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "Child{" +
                "Id=" + Id +
                '}';
    }
}

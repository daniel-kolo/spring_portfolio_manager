package com.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Parent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int Id;

    @OneToMany(
            mappedBy = "parent",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch=FetchType.EAGER

    )
    private List<Child> children = new ArrayList<Child>();

    public Parent(){};

    public void addChild(Child child){
        this.children.add(child);
    }

    public List<Child> getChildren(){
        return children;
    }

    @Override
    public String toString() {
        return "Parent{" +
                "Id=" + Id +
                ", children=" + children.size() +
                '}';
    }
}

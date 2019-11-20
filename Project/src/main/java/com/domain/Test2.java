package com.domain;

import javax.persistence.*;

@Entity
@Table(name = "test2")
public class Test2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int OwnId;

    @OneToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "test_id")
    private Test test;

    public Test2(){}

    public int getId() {
        return OwnId;
    }

    public Test getTest() {
        return test;
    }

    public void setId(int id) {
        OwnId = id;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    @Override
    public String toString() {
        return "Test2{" +
                "Own=" + OwnId +
                ", test=" + test.getId() +
                '}';
    }
}


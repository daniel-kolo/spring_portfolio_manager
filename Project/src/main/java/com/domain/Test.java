package com.domain;

import javax.persistence.*;

@Entity
@Table(name = "test")
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int Id;

    @OneToOne(
            mappedBy = "test",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private Test2 test2;

    public Test(){}

    public Test( Test2 test2) {
        //d = id;
        this.test2 = test2;
    }

    public int getId() {
        return Id;
    }

    public Test2 getTest2() {
        return test2;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setTest2(Test2 test2) {
        this.test2 = test2;
    }

    @Override
    public String toString() {
        return "Test{" +
                "Id=" + Id +
                ", test2=" + test2 +
                '}';
    }
}

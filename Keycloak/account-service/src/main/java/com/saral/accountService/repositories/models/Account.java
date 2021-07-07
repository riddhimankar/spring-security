package com.saral.accountService.repositories.models;



import javax.persistence.*;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private final int id;

    @Column(name = "acc_num")
    private final String number;

    @Column(name = "acc_name")
    private final String name;

    @Column(name = "acc_bal")
    private final float balance;

    public Account(int id, String number, String name, float balance) {
        this.id = id;
        this.number = number;
        this.name = name;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public float getBalance() {
        return balance;
    }
}

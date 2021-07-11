package com.saral.keycloak.client.dtos;

public class AccountDto {

    private String number;
    private String name;
    private float balance;

    public AccountDto(){

    }

    public AccountDto(String number, String name, float balance) {
        this.number = number;
        this.name = name;
        this.balance = balance;
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

    public void setNumber(String number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "AccountDto{" + "number='" + number + '\'' + ", name='" + name + '\'' + ", balance" +
                "=" + balance + '}';
    }
}

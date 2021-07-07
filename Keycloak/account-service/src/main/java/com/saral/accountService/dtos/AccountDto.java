package com.saral.accountService.dtos;

public class AccountDto {

    private final String number;
    private final String name;
    private final float balance;

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
}

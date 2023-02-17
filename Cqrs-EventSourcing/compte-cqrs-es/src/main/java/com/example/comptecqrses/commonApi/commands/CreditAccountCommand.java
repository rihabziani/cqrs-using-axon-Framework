package com.example.comptecqrses.commonApi.commands;

import lombok.Getter;

public class CreditAccountCommand extends BaseCommande<String>{
    @Getter private double amount;
    @Getter private String currency;
    public CreditAccountCommand(String id, double amount, String currency) {
        super(id);
        this.amount = amount;
        this.currency = currency;
    }

}

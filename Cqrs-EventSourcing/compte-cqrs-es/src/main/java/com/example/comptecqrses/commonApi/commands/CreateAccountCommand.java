package com.example.comptecqrses.commonApi.commands;

import lombok.Getter;

public class CreateAccountCommand extends BaseCommande<String>{
    @Getter private double initialBalance;
    @Getter private String currency;
    public CreateAccountCommand(String id, double initialBalance, String currency) {
        super(id);
        this.initialBalance = initialBalance;
        this.currency = currency;
    }

}

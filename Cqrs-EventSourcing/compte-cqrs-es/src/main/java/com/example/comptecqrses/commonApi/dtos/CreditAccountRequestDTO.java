package com.example.comptecqrses.commonApi.dtos;

import lombok.Data;

@Data
public class CreditAccountRequestDTO {
    private String accountId;
    private double amount;
    private  String currency;
}

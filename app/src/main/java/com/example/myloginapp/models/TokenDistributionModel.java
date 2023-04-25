package com.example.myloginapp.models;

public class TokenDistributionModel {
    String username;
    String amount;

    public TokenDistributionModel(String username, String amount) {
        this.username = username;
        this.amount = amount;
    }

    public String getUsername() {
        return username;
    }

    public String getAmount() {
        return amount;
    }
}

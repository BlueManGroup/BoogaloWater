package com.example.myloginapp.models;

import java.util.Map;

public class LogsModel {

    String date;
    String action;
    String initiator;
    String receiver;
    String role;

    String tokens;

    public LogsModel(Map<String, String> data) {
        this.date = data.getOrDefault("date", null);
        this.action = data.getOrDefault("action", null);
        this.initiator = data.getOrDefault("initiator", null);;
        this.receiver = data.getOrDefault("receiver", null);;
        this.role = data.getOrDefault("role", null);;
        this.tokens = data.getOrDefault("tokens", null);
    }


    public String getDate() {
        return date;
    }

    public String getAction() {
        return action;
    }

    public String getInitiator() {
        return initiator;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getRole() {
        return role;
    }









}



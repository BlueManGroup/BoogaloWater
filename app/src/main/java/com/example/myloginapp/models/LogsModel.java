package com.example.myloginapp.models;

public class LogsModel {

    String date;
    String action;
    String initiator;
    String receiver;
    String role;



    public LogsModel(String date, String action, String initiator, String receiver, String role) {
        this.date = date;
        this.action = action;
        this.initiator = initiator;
        this.receiver = receiver;
        this.role = role;
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



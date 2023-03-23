package com.example.myloginapp;

public class ReqObj {
    String username;
    String password;

    public ReqObj(String username, String password) {
        this.password = password;
        this.username = username;
    }
    public String toString() {
        return "{\"username\":\"" + username + "\",\"password\":\"" + password + "\"}";
    }
}

package com.example.myloginapp;
import java.util.Map;

public class ReqObj {
    String username;
    String password;
    String token;

    public ReqObj(Map<String, String> data) {
        this.username = data.get("username");
        this.password = data.get("password");
        this.token = data.get("token");
    }

    public String toString() {
        return "{\"username\":\"" + username + "\",\"password\":\"" + password + "\",\"token\":\"" + token + "\"}";
    }
}
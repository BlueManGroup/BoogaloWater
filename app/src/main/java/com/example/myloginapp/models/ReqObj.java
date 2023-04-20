package com.example.myloginapp.models;
import java.util.Map;

public class ReqObj {
    String username;
    String password;
    String token;
    String role;

    public ReqObj(Map<String, String> data) {
        this.username = data.getOrDefault("username", null);
        this.password = data.getOrDefault("password", null);
        this.token = data.getOrDefault("token", null);
        this.role = data.getOrDefault("role", null);
    }

    public String toString() {
        return "{\"username\":\"" + username + "\",\"password\":\"" + password + "\",\"token\":\"" + token + "\",\"updatedRole\":\"" + role + "\"}";
    }
}
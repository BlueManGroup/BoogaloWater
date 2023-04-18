package com.example.myloginapp.models;

public class CurrentUser {
    static String username;
    static String role;

    public CurrentUser(String username, String role) {
        CurrentUser.username = username;
        CurrentUser.role = role;
    }

    public  CurrentUser() {

    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }
}

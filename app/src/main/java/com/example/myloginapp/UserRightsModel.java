package com.example.myloginapp;

public class UserRightsModel {
    String username;
    String role;
    //int profilePicture;

    public UserRightsModel(String username, String role) {
        this.username = username;
        this.role = role;
        //this.profilePicture = profilePicture;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    //public int getProfilePicture() { return profilePicture; }

    // Find a way to 1distinguish buttons if necessary
}

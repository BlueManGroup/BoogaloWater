package com.example.myloginapp;
import androidx.annotation.NonNull;

public class ReqObj {
    int objType; // Keeps track of which constructor was used. Is needed to print correct value in the toString method.
    TokenManager tokenManager; // For getting the jwt.
    String username;
    String password;
    String jwt;


    public ReqObj() { // No-args constructor gets the jwt, so we don't have to do it in each file.
        this.jwt = tokenManager.getJwtToken();
        this.objType = 0;
    }

    public ReqObj(String username, String password) { // If two strings are parsed, this constructor will run instead.
        this.password = password;
        this.username = username;
        this.objType = 1;
    }


    @NonNull
    public String toString() {
        switch (objType) { // Switch: In case more versions of this object is needed.
            case 0:
                return "{\"jwt\":\"" + jwt + "\"}";
            case 1:
                return "{\"username\":\"" + username + "\",\"password\":\"" + password + "\"}";
            default:
                break;
        }
        return "Error in switch statement, maybe...";
    }
}

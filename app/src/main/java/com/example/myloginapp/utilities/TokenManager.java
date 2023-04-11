package com.example.myloginapp.utilities;

import android.content.Context;
import android.content.SharedPreferences;

public class TokenManager {

    private static final String SHARED_PREF_NAME = "my_secure_shared_pref";
    private static final String KEY_JWT_TOKEN = "jwt_token";

    private SharedPreferences sharedPreferences;

    public TokenManager(Context context) {
        this.sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
    }

    public void saveJwtToken(String jwtToken) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_JWT_TOKEN, jwtToken);
        editor.apply();
    }

    public String getJwtToken() {
        return sharedPreferences.getString(KEY_JWT_TOKEN, null);
    }

    public void clearJwtToken() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(KEY_JWT_TOKEN);
        editor.apply();
    }

}
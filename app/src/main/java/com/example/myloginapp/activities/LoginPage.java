package com.example.myloginapp.activities;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myloginapp.R;
import com.example.myloginapp.utilities.RequestHandler;
import com.example.myloginapp.utilities.TokenManager;
import com.example.myloginapp.utilities.ReqObj;

import java.util.HashMap;
import java.util.concurrent.Future;
import java.util.Map;



public class LoginPage extends AppCompatActivity {

    TokenManager tokenManager;
    String token;
    EditText username;
    EditText password;
    Button loginButton;
    Button loginButtonAAU;
    Button NotAUserButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        username = findViewById(R.id.username); //poop
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        loginButtonAAU = findViewById(R.id.loginButtonAAU);
        NotAUserButton = findViewById(R.id.notAUserButton);
        tokenManager = new TokenManager(this);

        // Login Button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Map<String, String> data = new HashMap<String, String>();
                data.put("username", username.getText().toString());
                data.put("password", password.getText().toString());
                ReqObj obj = new ReqObj(data);
                Future<Object> res = RequestHandler.postJson(obj, "login");

                try {
                    Map<String, Object> resMap = (Map<String, Object>) res.get();
                    Map<String, Object> dataMap = (Map<String, Object>) resMap.get("data");
                    token = (String) dataMap.get("token");
                    tokenManager.saveJwtToken(token);
                    System.out.println("Token from request: " + token);


                    Toast.makeText(LoginPage.this, "You have logged in", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginPage.this, HomePage.class);

                    System.out.println("Token from storage: " + tokenManager.getJwtToken());
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(LoginPage.this, "Error while logging in!", Toast.LENGTH_SHORT).show();
                    System.out.println(e);
                }

            }


        });



        // Redirect to Sign Up Page
        NotAUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginPage.this, SignUpPage.class);
                startActivity(intent);
            }
        });

        // SSO to AAU Login
        loginButtonAAU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginPage.this, "AAU is not developed, yet", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
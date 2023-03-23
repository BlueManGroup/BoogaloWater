package com.example.myloginapp;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.myloginapp.RequestHandler;

import java.util.concurrent.Future;
import java.util.Map;



public class LoginPage extends AppCompatActivity {
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

        // Login Button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ReqObj obj = new ReqObj(username.getText().toString(), password.getText().toString());
                Future<Object> res = RequestHandler.postJson(obj, "login");
                String token = null;

                try {
                    Map<String, Object> resMap = (Map<String, Object>) res.get();
                    Map<String, Object> dataMap = (Map<String, Object>) resMap.get("data");
                    token = (String) dataMap.get("token");
                    System.out.println(token);

                    Intent intent = new Intent(LoginPage.this, HomePage.class);
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(LoginPage.this, "Error while loggin in!", Toast.LENGTH_SHORT).show();
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
package com.example.myloginapp;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
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
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        loginButtonAAU = findViewById(R.id.loginButtonAAU);
        NotAUserButton = findViewById(R.id.notAUserButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().equals("user") && password.getText().toString().equals("1234")) {
                    Toast.makeText(LoginPage.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginPage.this, "Login Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        NotAUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginPage.this, SignUpPage.class);
                startActivity(intent);
            }
        });

        loginButtonAAU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginPage.this, "AAU is not developed, yet", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
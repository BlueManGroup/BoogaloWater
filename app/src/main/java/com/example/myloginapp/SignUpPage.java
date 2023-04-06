package com.example.myloginapp;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class SignUpPage extends AppCompatActivity {
    EditText username;
    EditText password;
    Button loginButton;
    Button loginButtonAAU;
    Button AlreadyUserButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        AlreadyUserButton = findViewById(R.id.alreadyUserButton);
        loginButtonAAU = findViewById(R.id.SignUpButtonAAU);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SignUpPage.this, "Login Successfully", Toast.LENGTH_SHORT).show();
            }
        });
        AlreadyUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpPage.this, LoginPage.class);
                startActivity(intent);
            }
        });
        loginButtonAAU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SignUpPage.this, "The feature is not implemented, yet", Toast.LENGTH_SHORT).show();
            }});
    }
}
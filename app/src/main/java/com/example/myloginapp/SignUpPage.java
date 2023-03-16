package com.example.myloginapp;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
public class SignUpPage extends AppCompatActivity {
    EditText username;
    EditText password;
    Button loginButton;
    Button loginButtonAAU;
    Button AlreadyAUserButton;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        loginButtonAAU = findViewById(R.id.loginButtonAAU);
        AlreadyAUserButton = findViewById(R.id.AUserButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().equals("user") && password.getText().toString().equals("1234")) {
                    Toast.makeText(SignUpPage.this, "Sign Up Successful!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SignUpPage.this, "Sign Up Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        AlreadyAUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_login_page);
            }
        });

        loginButtonAAU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SignUpPage.this, "The feature is not implemented, yet", Toast.LENGTH_SHORT).show();
            }});
    }
}
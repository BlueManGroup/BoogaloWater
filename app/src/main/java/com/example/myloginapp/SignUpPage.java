package com.example.myloginapp;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.concurrent.Future;
import java.util.Map;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SignUpPage extends AppCompatActivity {
    TokenManager tokenManager;
    EditText username;
    EditText password;
    Button signupButton;
    Button loginButtonAAU;
    Button AlreadyUserButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        signupButton = findViewById(R.id.signupButton);
        AlreadyUserButton = findViewById(R.id.alreadyUserButton);
        loginButtonAAU = findViewById(R.id.SignUpButtonAAU);
        tokenManager = new TokenManager(this);


        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Map<String, String> data = new HashMap<String, String>();
                data.put("username", username.getText().toString());
                data.put("password", password.getText().toString());
                ReqObj obj = new ReqObj(data);
                Future<Object> res = RequestHandler.postJson(obj, "signup");
                String token;

                try {
                    Map<String, Object> resMap = (Map<String, Object>) res.get();
                    token = (String) resMap.get("token");
                    if (token == null) throw new Exception("fuck");
                    tokenManager.saveJwtToken(token);
                    Intent intent = new Intent(SignUpPage.this, HomePage.class);
                    startActivity(intent);

                } catch (Exception e) {
                    Toast.makeText(SignUpPage.this, "Error while signing up", Toast.LENGTH_SHORT).show();
                    System.out.println(e);
                }

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
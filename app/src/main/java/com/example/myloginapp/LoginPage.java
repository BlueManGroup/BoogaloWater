package com.example.myloginapp;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.myloginapp.RequestHandler;



public class LoginPage extends AppCompatActivity {
    EditText username;
    EditText password;
    Button loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        username = findViewById(R.id.username); //poop
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReqObj obj = new ReqObj(username.getText().toString(), password.getText().toString());
                if (RequestHandler.postJson(obj.toString())) {
                    Toast.makeText(LoginPage.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginPage.this, "Login Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public class ReqObj {
        String username;
        String password;

        public ReqObj(String username, String password) {
            this.username = username;
            this.password = password;
        }
        public String toString() {
            return "{\"username\":\"" + username + "\",\"password\":\"" + password + "\"}";
        }
    }
}
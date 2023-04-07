package com.example.myloginapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import android.widget.Button;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

public class HomePage extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    Button Redeembutton;
    TokenManager tokenManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        tokenManager = new TokenManager(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Redeembutton = findViewById(R.id.redeem);

        /*------------------------Hooks----------------------------------------*/
        drawerLayout = findViewById(R.id.drawerHomePage);
        navigationView = findViewById(R.id.navigationView);
        toolbar = findViewById(R.id.home_page_toolbar);
        navigationView.setItemIconTintList(null);

        /*------------------------Toolbar----------------------------------------*/
        setSupportActionBar(toolbar);

        /*------------------------Navigation Drawer Menu----------------------------*/
        ActionBarDrawerToggle toggle =  new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        Redeembutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    String token = tokenManager.getJwtToken().toString();
                    Map<String, String> data = new HashMap<String, String>();
                    data.put("username", "NULL");
                    data.put("password", "NULL");
                    data.put("token", tokenManager.getJwtToken());
                    System.out.println(token);
                    ReqObj obj = new ReqObj(data);
                    Future<Object> res = RequestHandler.postJson(obj, "tokens/redeem");
                } catch(Exception e) {
                    Toast.makeText(HomePage.this, "Error redeeming token!", Toast.LENGTH_SHORT).show();
                    System.out.println(e);
                }
            }


        });


    }

}
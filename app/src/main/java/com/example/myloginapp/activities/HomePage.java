package com.example.myloginapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.View;
import android.widget.Toast;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;

import com.example.myloginapp.R;
import com.example.myloginapp.utilities.RequestHandler;
import com.example.myloginapp.utilities.TokenManager;
import com.example.myloginapp.models.ReqObj;
import com.google.android.material.navigation.NavigationView;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;
import java.lang.Integer;

public class HomePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
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

        /*------------------------Hooks----------------------------------------*/
        drawerLayout = findViewById(R.id.drawerHomePage);
        navigationView = findViewById(R.id.navigationView);

        toolbar = findViewById(R.id.home_page_toolbar);
        Redeembutton = findViewById(R.id.redeemButton);
        navigationView.setItemIconTintList(null);

        /*------------------------Toolbar----------------------------------------*/
        setSupportActionBar(toolbar);

        /*------------------------Navigation Drawer Menu----------------------------*/
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        /*------------------request setup------------*/
        String token = tokenManager.getJwtToken().toString();
        Map<String, String> data = new HashMap<String, String>();
        data.put("token", tokenManager.getJwtToken());
        ReqObj obj = new ReqObj(data);

        /*-----------------------Token amount display------------------*/
        TextView tokenTextView = findViewById(R.id.tokenText);

        try {
            Future<Object> res = RequestHandler.postJson(obj, "account/info");
            Map<String, Object> resMap = (Map<String, Object>) res.get();
            Map<String, Object> objMap = (Map<String, Object>) resMap.get("response");
            double tokens = (double) objMap.get("tokens");
            tokenTextView.setText("Tokens: " + (int) tokens);
        } catch(Exception e) {
            System.out.println(e);
        }


        Redeembutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    Future<Object> redeemRes = RequestHandler.postJson(obj, "tokens/redeem");
                    Map<String, Object> redeemResMap = (Map<String, Object>) redeemRes.get();
                    System.out.println(redeemResMap);
                    double tokens = (double) redeemResMap.get("response");
                    tokenTextView.setText("Tokens: " + (int) tokens);
                } catch(Exception e) {
                    Toast.makeText(HomePage.this, "Error redeeming token!", Toast.LENGTH_SHORT).show();
                    System.out.println(e);
                }
            }


        });


    }

    /*------------------------Navigation Drawer Menu ----------------------------*/
    @Override
    /* Makes it possible to close the drawer when clicking on Back Button on Phone instead of
     * returning to Login Page */
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menuHome:
                break;
            case R.id.menuTokens:
                break;
            case R.id.menuTokenDistribution:
                break;
            case R.id.menuUserRights:
                Toast.makeText(this, "Switching to User Rights", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(HomePage.this, UserRightsPage.class);
                startActivity(intent);
                break;
            case R.id.menuLogs:
                Toast.makeText(this, "Switching to User Rights", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(HomePage.this, LogsPage.class);
                startActivity(intent2);
                break;
            case R.id.menuLogout:
                Toast.makeText(this, "Switching to User Rights", Toast.LENGTH_SHORT).show();
                Intent intent3 = new Intent(HomePage.this, LoginPage.class);
                startActivity(intent3);
                break;
        }
        return true;
    }
}





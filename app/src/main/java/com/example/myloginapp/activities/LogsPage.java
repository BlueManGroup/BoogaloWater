package com.example.myloginapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;

import com.example.myloginapp.R;
import com.example.myloginapp.models.CurrentUser;
import com.example.myloginapp.models.LogsModel;
import com.example.myloginapp.utilities.RequestHandler;
import com.example.myloginapp.utilities.TokenManager;
import com.example.myloginapp.models.ReqObj;
import com.google.android.material.navigation.NavigationView;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class LogsPage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    TokenManager tokenManager;
    String token;

    String urlStr;

    String[] actions = {"change user rights","redeem","distribute"};

    CurrentUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        tokenManager = new TokenManager(this);

        currentUser = new CurrentUser();


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        /*------------------------Hooks----------------------------------------*/
        drawerLayout = findViewById(R.id.drawerHomePage);
        navigationView = findViewById(R.id.navigationView);

        toolbar = findViewById(R.id.home_page_toolbar);
        navigationView.setItemIconTintList(null);

        /*------------------------Toolbar----------------------------------------*/
        setSupportActionBar(toolbar);

        /*------------------------Navigation Drawer Menu----------------------------*/
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


        /*----------------------Get Logs------------------------------------------*/

        token = tokenManager.getJwtToken();

        


        Map<String, String> data = new HashMap<String, String>();
        data.put("token", token);
        data.put("action",actions.toString());
        ReqObj obj = new ReqObj(data);

        if(currentUser.getRole() == "director") {
            urlStr = "director/log";
        } else {
            urlStr = "account/log";
        }

        Future<Object> res = RequestHandler.postJson(obj,urlStr);

        try {
            Map<String, Object> resMap = (Map<String, Object>) res.get(); // Get the response object from future object. Output: {bool: , data: [{},{},...]}
            // The response objects 'data'-field contains an array with objects. One for each user
            // containing the username and the role of the user.
            // It is unpacked like this:
            ArrayList<Map<String, Object>> dataMap = (ArrayList<Map<String, Object>>) resMap.get("data"); // Get the array from 'data'-field. Output: [{username: , role: },{},... ]

            // Goes through the items in dataMap, which is an array of Map-objects, to store
            // their data in a UserRightsModel, so that it can be used by the recycler view.
            for (Map<String, Object> dataObj : dataMap) {
                LogsModel.add(new UserRightsModel(dataObj.get("username").toString(), dataObj.get("role").toString()));
            }

        } catch (Exception e) {
            Toast.makeText(UserRightsPage.this, "Error while loading the user rights page!", Toast.LENGTH_SHORT).show();
            System.out.println(e);
        } */
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
                Toast.makeText(this, "Switching to Home Page", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LogsPage.this, HomePage.class);
                startActivity(intent);
                break;
            case R.id.menuTokens:
                break;
            case R.id.menuTokenDistribution:
                break;
            case R.id.menuUserRights:
                Toast.makeText(this, "Switching to User Rights", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(LogsPage.this, UserRightsPage.class);
                startActivity(intent2);
                break;
            case R.id.menuLogs:
                break;
        }
        return true;
    }
}





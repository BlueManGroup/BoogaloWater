package com.example.myloginapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;

import com.example.myloginapp.R;
import com.example.myloginapp.adapters.LogAdapter;
import com.example.myloginapp.adapters.URP_RecyclerViewAdapter;
import com.example.myloginapp.models.CurrentUser;
import com.example.myloginapp.models.LogsModel;
import com.example.myloginapp.models.UserRightsModel;
import com.example.myloginapp.utilities.RequestHandler;
import com.example.myloginapp.utilities.TokenManager;
import com.example.myloginapp.models.ReqObj;
import com.google.android.material.navigation.NavigationView;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
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
    ArrayList<LogsModel> logsModels = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        tokenManager = new TokenManager(this);

        currentUser = new CurrentUser();


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logs_page);

        /*------------------------Hooks----------------------------------------*/
        drawerLayout = findViewById(R.id.drawerLogsPage);
        navigationView = findViewById(R.id.navigationView);

        toolbar = findViewById(R.id.logs_page_toolbar);
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


        /*------------------------Recycler View----------------------------*/
        RecyclerView recyclerView = findViewById(R.id.logRecyclerView);

        setUpLogsModels();

        LogAdapter adapter = new LogAdapter(this, logsModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    private void setUpLogsModels() {
        token = tokenManager.getJwtToken();




        Map<String, String> data = new HashMap<String, String>();
        data.put("token", token);
        data.put("action",actions.toString()); // tager den her hele arrayet som en string? backenden fetcher allerede alt, hvis man passer null
        ReqObj obj = new ReqObj(data);

        if(Objects.equals(currentUser.getRole(), "director")) {
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



            ArrayList<Map<String, Object>> dataMap = (ArrayList<Map<String, Object>>) resMap.get("response"); // Get the array from 'data'-field. Output: [{username: , role: },{},... ]

            // Goes through the items in dataMap, which is an array of Map-objects, to store
            // their data in a UserRightsModel, so that it can be used by the recycler view.
            System.out.println(dataMap);
            Integer i = 0;
            assert dataMap != null;
            for (Map<String, Object> dataObj : dataMap) {
                System.out.print("create object");
                System.out.println(i);
                logsModels.add(new LogsModel(dataObj));
                i++;
            }






        } catch (Exception e) {
            Toast.makeText(LogsPage.this, "Error while loading the user rights page!", Toast.LENGTH_SHORT).show();
            System.out.println(e);
        }
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
                Intent toHomeIntent = new Intent(LogsPage.this, HomePage.class);
                startActivity(toHomeIntent);
                break;
            case R.id.menuTokenDistribution:
                Toast.makeText(this, "Switching to Token Distribution Page", Toast.LENGTH_SHORT).show();
                Intent toDistrIntent = new Intent(LogsPage.this, TokenDistributionPage.class);
                startActivity(toDistrIntent);
                break;
            case R.id.menuUserRights:
                Toast.makeText(this, "Switching to User Rights", Toast.LENGTH_SHORT).show();
                Intent toUsrRightsIntent = new Intent(LogsPage.this, UserRightsPage.class);
                startActivity(toUsrRightsIntent);
                break;
            case R.id.menuLogs:
                break;
            case R.id.menuSettings:
                Toast.makeText(this, "Switching to Settings Page", Toast.LENGTH_SHORT).show();
                Intent toSettingsIntent = new Intent(LogsPage.this, SettingsPage.class);
                startActivity(toSettingsIntent);
                break;
            case R.id.menuLogout:
                Toast.makeText(this, "Logging out", Toast.LENGTH_SHORT).show();
                Intent toLogoutIntent = new Intent(LogsPage.this, LoginPage.class);
                startActivity(toLogoutIntent);
                break;
        }
        return true;
    }
}





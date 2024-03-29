package com.example.myloginapp.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;

import com.example.myloginapp.R;
import com.example.myloginapp.models.UserRightsModel;
import com.google.android.material.navigation.NavigationView;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.widget.Toast;

import com.example.myloginapp.utilities.RequestHandler;
import com.example.myloginapp.utilities.TokenManager;
import com.example.myloginapp.models.ReqObj;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myloginapp.adapters.URP_RecyclerViewAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.HashMap;
import java.util.concurrent.Future;
import java.util.Map;
import java.util.ArrayList;


public class UserRightsPage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    TokenManager tokenManager;

    // Array that stores the models that let us show the list of users on the screen.
    ArrayList<UserRightsModel> userRightsModels = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_rights_page);

        /*------------------------Hooks----------------------------------------*/
        drawerLayout = findViewById(R.id.userRightsPage);
        navigationView = findViewById(R.id.navigationView);
        toolbar = findViewById(R.id.user_rights_page_toolbar);
        navigationView.setItemIconTintList(null);

        /*------------------------Toolbar----------------------------------------*/
        setSupportActionBar(toolbar);

        /*------------------------Navigation Drawer Menu----------------------------*/
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        /*------------------------Recycler View----------------------------*/
        RecyclerView recyclerView = findViewById(R.id.mRecyclerView);

        setUpUserRightsModels();

        URP_RecyclerViewAdapter adapter = new URP_RecyclerViewAdapter(this, userRightsModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private void setUpUserRightsModels() {
        // Use RequestHandler to get an array of all usernames and their corresponding role.
        tokenManager = new TokenManager(this);

        Map<String, String> data = new HashMap<String, String>();
        data.put("token", tokenManager.getJwtToken());

        ReqObj obj = new ReqObj(data);

        try {
            Future<Object> res = RequestHandler.postJson(obj, "director/showall");
            Map<String, Object> resMap = (Map<String, Object>) res.get(); // Get the response object from future object. Output: {bool: , data: [{},{},...]}
            // The response objects 'data'-field contains an array with objects. One for each user
            // containing the username and the role of the user.
            // It is unpacked like this:
            System.out.println(resMap);
            ArrayList<Map<String, Object>> dataMap = (ArrayList<Map<String, Object>>) resMap.get("response"); // Get the array from 'data'-field. Output: [{username: , role: },{},... ]

            // Goes through the items in dataMap, which is an array of Map-objects, to store
            // their data in a UserRightsModel, so that it can be used by the recycler view.
            for (Map<String, Object> dataObj : dataMap) {
                userRightsModels.add(new UserRightsModel(dataObj.get("username").toString(), dataObj.get("role").toString()));
            }

        } catch (Exception e) {
            Toast.makeText(UserRightsPage.this, "Error while loading the user rights page!", Toast.LENGTH_SHORT).show();
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
                Intent toHomeIntent = new Intent(UserRightsPage.this, HomePage.class);
                startActivity(toHomeIntent);
                break;
            case R.id.menuTokenDistribution:
                Toast.makeText(this, "Switching to Token Distribution Page", Toast.LENGTH_SHORT).show();
                Intent toDistrIntent = new Intent(UserRightsPage.this, TokenDistributionPage.class);
                startActivity(toDistrIntent);
                break;
            case R.id.menuUserRights:
                break;
            case R.id.menuLogs:
                Toast.makeText(this, "Switching to Logs Page", Toast.LENGTH_SHORT).show();
                Intent toLogsIntent = new Intent(UserRightsPage.this, LogsPage.class);
                startActivity(toLogsIntent);
                break;
            case R.id.menuSettings:
                Toast.makeText(this, "Switching to Settings Page", Toast.LENGTH_SHORT).show();
                Intent toSettingsIntent = new Intent(UserRightsPage.this, SettingsPage.class);
                startActivity(toSettingsIntent);
                break;
            case R.id.menuLogout:
                Toast.makeText(this, "Logging out", Toast.LENGTH_SHORT).show();
                Intent toLogoutIntent = new Intent(UserRightsPage.this, LoginPage.class);
                startActivity(toLogoutIntent);
                break;
        }
        return true;
    }
}


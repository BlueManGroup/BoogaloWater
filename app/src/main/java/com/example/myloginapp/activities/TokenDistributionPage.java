package com.example.myloginapp.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;

import com.example.myloginapp.R;
import com.example.myloginapp.adapters.TDP_RecyclerViewAdapter;
import com.example.myloginapp.models.TokenDistributionModel;
import com.example.myloginapp.models.UserRightsModel;
import com.example.myloginapp.utilities.TotalTokensHandler;
import com.google.android.material.navigation.NavigationView;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.myloginapp.utilities.RequestHandler;
import com.example.myloginapp.utilities.TokenManager;
import com.example.myloginapp.models.ReqObj;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.HashMap;
import java.util.concurrent.Future;
import java.util.Map;
import java.util.ArrayList;


public class TokenDistributionPage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    TokenManager tokenManager;
    TotalTokensHandler totalTokensHandler;

    ArrayList<TokenDistributionModel> tokenDistributionModels = new ArrayList<>();
    


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distribution_page);

        /*------------------------Hooks----------------------------------------*/
        drawerLayout = findViewById(R.id.tokenDistributionPage);
        navigationView = findViewById(R.id.navigationView);
        toolbar = findViewById(R.id.token_distribution_page_toolbar);
        navigationView.setItemIconTintList(null);
        totalTokensHandler = new TotalTokensHandler(findViewById(R.id.totalTokenAmount));
        /*------------------------Toolbar----------------------------------------*/
        setSupportActionBar(toolbar);

        /*------------------------Navigation Drawer Menu----------------------------*/
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        /*------------------------Recycler View----------------------------*/
        RecyclerView recyclerView = findViewById(R.id.tokenRecyclerView);

        setUpTokenDistributionModel();

        TDP_RecyclerViewAdapter adapter = new TDP_RecyclerViewAdapter(this, tokenDistributionModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private void setUpTokenDistributionModel() {
        // Get an array of all usernames and their corresponding token assets.
        tokenManager = new TokenManager(this);
        totalTokensHandler.setTotalTokensText(tokenManager);

        Map<String, String> data = new HashMap<String, String>();
        data.put("token", tokenManager.getJwtToken());

        ReqObj obj = new ReqObj(data);

        try {
            Future<Object> res = RequestHandler.postJson(obj, "director/tokens");
            Map<String, Object> resMap = (Map<String, Object>) res.get(); // Get the response object from future object. Output: {bool: , data: [{},{},...]}
            // The response objects 'data'-field contains an array with objects. One for each user
            // containing the username and the role of the user.
            // It is unpacked like this:
            ArrayList<Map<String, String>> dataMap = (ArrayList<Map<String, String>>) resMap.get("response"); // Get the array from 'data'-field. Output: [{username: , role: },{},... ]
            // Goes through the items in dataMap, which is an array of Map-objects, to store
            // their data in a TokenDistributionModel, so that it can be used by the recycler view.
            for (Map<String, String> dataObj : dataMap) {
                tokenDistributionModels.add(new TokenDistributionModel(dataObj.get("username"), dataObj.get("tokens").toString()));
            }

        } catch (Exception e) {
            Toast.makeText(TokenDistributionPage.this, "Error while loading the token distribution page!", Toast.LENGTH_SHORT).show();
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
                Intent toHomeIntent = new Intent(TokenDistributionPage.this, HomePage.class);
                startActivity(toHomeIntent);
                break;
            case R.id.menuTokenDistribution:
                Toast.makeText(this, "Switching to Token Distribution Page", Toast.LENGTH_SHORT).show();
                Intent toDistrIntent = new Intent(TokenDistributionPage.this, TokenDistributionPage.class);
                startActivity(toDistrIntent);
                break;
            case R.id.menuUserRights:
                Toast.makeText(this, "Switching to User Rights", Toast.LENGTH_SHORT).show();
                Intent toUsrRightsIntent = new Intent(TokenDistributionPage.this, UserRightsPage.class);
                startActivity(toUsrRightsIntent);
                break;
            case R.id.menuLogs:
                Toast.makeText(this, "Switching to Logs Page", Toast.LENGTH_SHORT).show();
                Intent toLogsIntent = new Intent(TokenDistributionPage.this, LogsPage.class);
                startActivity(toLogsIntent);
                break;
            case R.id.menuSettings:
                Toast.makeText(this, "Switching to Settings Page", Toast.LENGTH_SHORT).show();
                Intent toSettingsIntent = new Intent(TokenDistributionPage.this, SettingsPage.class);
                startActivity(toSettingsIntent);
                break;
            case R.id.menuLogout:
                Toast.makeText(this, "Logging out", Toast.LENGTH_SHORT).show();
                Intent toLogoutIntent = new Intent(TokenDistributionPage.this, LoginPage.class);
                startActivity(toLogoutIntent);
                break;
        }
        return true;
    }
}


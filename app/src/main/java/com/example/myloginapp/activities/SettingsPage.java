package com.example.myloginapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;

import com.example.myloginapp.R;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

public class SettingsPage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);


        /*------------------------Hooks----------------------------------------*/
        drawerLayout = findViewById(R.id.drawerSettingsPage);
        navigationView = findViewById(R.id.navigationView);
        toolbar = findViewById(R.id.settings_page_toolbar);
        navigationView.setItemIconTintList(null);

        /*------------------------Toolbar----------------------------------------*/
        setSupportActionBar(toolbar);

        /*------------------------Navigation Drawer Menu----------------------------*/
        ActionBarDrawerToggle toggle =  new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        /* INSERT YOUR CODE HERE */

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menuHome:
                Toast.makeText(this, "Switching to Home Page", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SettingsPage.this, HomePage.class);
                startActivity(intent);
                break;
            case R.id.menuTokenDistribution:
                break;
            case R.id.menuUserRights:
                break;
            case R.id.menuLogs:
                Toast.makeText(this, "Switching to Logs Page", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(SettingsPage.this, LogsPage.class);
                startActivity(intent2);
                break;
            case R.id.menuLogout:
                Toast.makeText(this, "Switching to User Rights", Toast.LENGTH_SHORT).show();
                Intent intent3 = new Intent(SettingsPage.this, LoginPage.class);
                startActivity(intent3);
                break;
        }
        return true;
    }

}
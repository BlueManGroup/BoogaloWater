package com.example.myloginapp;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

public class SettingsPage extends AppCompatActivity {

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

}
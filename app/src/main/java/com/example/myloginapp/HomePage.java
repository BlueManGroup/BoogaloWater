package com.example.myloginapp;

import android.os.Bundle;
import android.view.View;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

public class HomePage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        // Home page layout
        final DrawerLayout drawerlayout = findViewById(R.id.drawerHomePage);

        // On Click Menu
        findViewById(R.id.imageMenu).setOnClickListener(new View.OnClickListener() {
            // On Click Open Drawer
            @Override
            public void onClick(View view) {
                drawerlayout.openDrawer(GravityCompat.START);
            }
        });

        NavigationView navigationView = findViewById(R.id.navigationView);
        navigationView.setItemIconTintList(null);


    }


}
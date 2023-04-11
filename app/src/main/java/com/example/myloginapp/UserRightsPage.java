package com.example.myloginapp;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import android.media.session.MediaSession;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Future;

public class UserRightsPage extends AppCompatActivity {
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
        ActionBarDrawerToggle toggle =  new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

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
        data.put("username", "NULL");
        data.put("password", "NULL");
        data.put("token", tokenManager.getJwtToken());

        ReqObj obj = new ReqObj(data);

        try {
            Future<Object> res = RequestHandler.postJson(obj, "director/showall");
            Map<String, Object> resMap = (Map<String, Object>) res.get(); // Get the response object from future object. Output: {bool: , data: [{},{},...]}
            // The response objects 'data'-field contains an array with objects. One for each user
            // containing the username and the role of the user.
            // It is unpacked like this:
            ArrayList<Map<String, Object>> dataMap = (ArrayList<Map<String, Object>>) resMap.get("data"); // Get the array from 'data'-field. Output: [{username: , role: },{},... ]

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

}


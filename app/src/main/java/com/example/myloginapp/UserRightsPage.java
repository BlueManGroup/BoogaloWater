package com.example.myloginapp;

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
    TokenManager tokenManager;

    // Array that stores the models that let us show the list of users on the screen.
    ArrayList<UserRightsModel> userRightsModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_rights_page);

        RecyclerView recyclerView = findViewById(R.id.mRecyclerView);

        setUpUserRightsModels();

        URP_RecyclerViewAdapter adapter = new URP_RecyclerViewAdapter(this, userRightsModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    private void setUpUserRightsModels() {
        // Use RequestHandler to get an array of all usernames and their corresponding role.
        // Nevermind: ReqObj obj = new ReqObj(); // Automatically initializes as the local jwt string.
        tokenManager = new TokenManager(this);
        String token = tokenManager.getJwtToken().toString();
        Map<String, String> data = new HashMap<String, String>();
        data.put("username", "NULL");
        data.put("password", "NULL");
        data.put("token", tokenManager.getJwtToken());
        ReqObj obj = new ReqObj(data);
        System.out.println(token);


        try {
            // 'res' should contain an array with objects like this: {"username", "role"}, for all users.
            Future<Object> res = RequestHandler.postJson(obj, "director/showall");
            //System.out.println(res.get());

            Map<String, Object> resMap = (Map<String, Object>) res.get();
            System.out.println(resMap);
            ArrayList<Map<String, Object>> dataMap = (ArrayList<Map<String, Object>>) resMap.get("data");
            System.out.println(resMap + "\n" +  dataMap);


            for (Map<String, Object> dataObj : dataMap) {
                System.out.println("{\"username\":\"" + dataObj.get("username") + "\",\"role\":\"" + dataObj.get("role") + "\"}");
                userRightsModels.add(new UserRightsModel(dataObj.get("username").toString(), dataObj.get("role").toString()));
            }

        } catch (Exception e) {
            Toast.makeText(UserRightsPage.this, "Error while loading the user rights page!", Toast.LENGTH_SHORT).show();
            System.out.println(e);
        }

    }

}

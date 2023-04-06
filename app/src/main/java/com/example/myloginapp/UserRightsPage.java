package com.example.myloginapp;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Future;


public class UserRightsPage extends AppCompatActivity {
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
        ReqObj obj = new ReqObj(); // Automatically initializes as the local jwt string.

        try {
            // 'res' should contain an array with objects like this: {"username", "role"}, for all users.
            Future<Object> res = RequestHandler.postJson(obj, "director/showall");
            System.out.println(res.get());

            Map<String, Object> resMap = (Map<String, Object>) res.get();
            String[] usernames = (String[]) resMap.get("username");
            String[] roles = (String[]) resMap.get("role");

            for (int i = 0; i < Objects.requireNonNull(usernames).length; i++) {
                System.out.println("{\"username\":\"" + usernames[i] + "\",\"role\":\"" + roles[i] + "\"}");

                userRightsModels.add(new UserRightsModel(usernames[i], roles[i]));
            }

        } catch (Exception e) {
            Toast.makeText(UserRightsPage.this, "Error while loading the user rights page!", Toast.LENGTH_SHORT).show();
            System.out.println(e);
        }

    }

}

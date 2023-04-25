package com.example.myloginapp.adapters;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.appcompat.widget.PopupMenu;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myloginapp.models.ReqObj;
import com.example.myloginapp.utilities.RequestHandler;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myloginapp.R;
import com.example.myloginapp.models.UserRightsModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;
import com.example.myloginapp.utilities.TokenManager;

public class URP_RecyclerViewAdapter extends RecyclerView.Adapter<URP_RecyclerViewAdapter.MyViewHolder> {
    Context context;
    ArrayList<UserRightsModel> userRightsModels;
    ReqObj reqObj;
    TokenManager tokenManager;
    PopupMenu popupMenu;
    MenuInflater menuInflater;

    public URP_RecyclerViewAdapter(Context context, ArrayList<UserRightsModel> userRightsModels) {
        this.context = context;
        this.userRightsModels = userRightsModels;
        tokenManager = new TokenManager(context);

    }

    @NonNull
    @Override
    public URP_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // This method inflates the layout, i.e. gives it its look.
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row, parent, false);

        return new URP_RecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull URP_RecyclerViewAdapter.MyViewHolder holder, int position) {
        String username = userRightsModels.get(position).getUsername();
        String role = userRightsModels.get(position).getRole();


        // This method assigns values to the views in the recycler_view_row layout file,
        // based on the position of the recycler view.
        holder.textView.setText(username);
        holder.button.setText(role);



        holder.button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    // Show Pop Up Menu

                    /*------------------------Pop up Menu with Inflater----------------------------*/
                    popupMenu = new PopupMenu(holder.button.getContext(), holder.button);
                    menuInflater = popupMenu.getMenuInflater();
                    menuInflater.inflate(R.menu.rolesmenu, popupMenu.getMenu());
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem menuItem) {
                            switch (menuItem.getItemId()) {
                                case R.id.rolesResponsible:
                                    System.out.println(username);
                                    try {
                                        String token = tokenManager.getJwtToken().toString();
                                        Map<String, String> data = new HashMap<String, String>();
                                        data.put("username", username);
                                        data.put("role", "responsible");
                                        data.put("token", token);

                                        reqObj = new ReqObj(data);
                                        Future<Object> res = RequestHandler.postJson(reqObj, "director/updateuserrole");

                                    } catch(Exception e) {
                                        Toast.makeText(holder.button.getContext(), "¿Esos son reebok o son nike?", Toast.LENGTH_SHORT).show();
                                        System.out.println(e);
                                    }
                                    holder.button.setText("responsible");
                                    return true;
                                case R.id.rolesUser:
                                    try {
                                        String token = tokenManager.getJwtToken().toString();
                                        Map<String, String> data = new HashMap<String, String>();
                                        data.put("username", username);
                                        data.put("role", "user");
                                        data.put("token", token);

                                        reqObj = new ReqObj(data);
                                        Future<Object> res = RequestHandler.postJson(reqObj, "director/updateuserrole");

                                    } catch(Exception e) {
                                        Toast.makeText(holder.button.getContext(), "¿Esos son reebok o son nike?", Toast.LENGTH_SHORT).show();
                                        System.out.println(e);
                                    }
                                    holder.button.setText("user");
                                    return true;
                                case R.id.rolesDirector:
                                    try {
                                        String token = tokenManager.getJwtToken().toString();
                                        Map<String, String> data = new HashMap<String, String>();
                                        data.put("username", username);
                                        data.put("role", "director");
                                        data.put("token", token);

                                        reqObj = new ReqObj(data);
                                        Future<Object> res = RequestHandler.postJson(reqObj, "director/updateuserrole");

                                    } catch(Exception e) {
                                        Toast.makeText(holder.button.getContext(), "¿Esos son reebok o son nike?", Toast.LENGTH_SHORT).show();
                                        System.out.println(e);
                                    }
                                    holder.button.setText("director");
                                    return true;
                                default:
                                    return false;
                            }

                        }
                    });

                    popupMenu.show();

                }
            });
        // Below set the profile picture. Ignore for now
        // holder.imageView.setImageResource(userRightsModels.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        // This method tells the recyclerView how many items it has to display.
        return userRightsModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        //ImageView imageView; // Will eventually hold the profile picture.
        TextView textView;
        Button button;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            //imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
            button = itemView.findViewById(R.id.button);
        }

    }
}

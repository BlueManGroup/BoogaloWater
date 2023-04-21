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

import com.example.myloginapp.activities.HomePage;
import com.example.myloginapp.activities.TokenDistributionPage;
import com.example.myloginapp.activities.UserRightsPage;
import com.example.myloginapp.models.ReqObj;
import com.example.myloginapp.models.TokenDistributionModel;
import com.example.myloginapp.models.UserRightsModel;
import com.example.myloginapp.utilities.RequestHandler;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myloginapp.R;
import com.example.myloginapp.models.TokenDistributionModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;
import com.example.myloginapp.utilities.TokenManager;

public class TDP_RecyclerViewAdapter extends RecyclerView.Adapter<TDP_RecyclerViewAdapter.MyViewHolder> {
    Context context;
    ArrayList<TokenDistributionModel> tokenDistributionModels;
    ReqObj reqObj;
    TokenManager tokenManager;

    public TDP_RecyclerViewAdapter(Context context, ArrayList<TokenDistributionModel> tokenDistributionModels) {
        this.context = context;
        this.tokenDistributionModels = tokenDistributionModels;
        tokenManager = new TokenManager(context);
        System.out.println(this.tokenDistributionModels);

    }

    @NonNull
    @Override
    public TDP_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // This method inflates the layout, i.e. gives it its look.
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_distribution, parent, false);

        return new TDP_RecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TDP_RecyclerViewAdapter.MyViewHolder holder, int position) {
        // This method assigns values to the views in the recycler_view_row layout file,
        // based on the position of the recycler view.
        String username = tokenDistributionModels.get(position).getUsername();
        String amount = tokenDistributionModels.get(position).getAmount();

        holder.usernameTextView.setText(username);
        holder.tokenAmountTextView.setText(amount + "tokens");
        // Below set the profile picture. Ignore for now
        // holder.imageView.setImageResource(userRightsModels.get(position).getImage());

        holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tokenManager = new TokenManager(v.getContext());


                    try {
                        String token = tokenManager.getJwtToken().toString();
                        Map<String, String> data = new HashMap<String, String>();
                        data.put("username", username);
                        data.put("amount", "1");
                        data.put("token", token);

                        reqObj = new ReqObj(data);
                        Future<Object> res = RequestHandler.postJson(reqObj, "tokens/create");

                    } catch(Exception e) {
                        Toast.makeText(holder.button.getContext(), "Esos son reebok o son nike?", Toast.LENGTH_SHORT).show();
                        System.out.println(e);
                    }
                    holder.button.setText("responsible");

                }

            });

    }

    @Override
    public int getItemCount() {
        // This method tells the recyclerView how many items it has to display.
        return tokenDistributionModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        //ImageView imageView; // Will eventually hold the profile picture.
        TextView usernameTextView;
        TextView tokenAmountTextView;
        Button button;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            //imageView = itemView.findViewById(R.id.imageView);
            usernameTextView = itemView.findViewById(R.id.username);
            tokenAmountTextView = itemView.findViewById(R.id.tokenAmount);
            button = itemView.findViewById(R.id.distributeButton);
        }

    }
}

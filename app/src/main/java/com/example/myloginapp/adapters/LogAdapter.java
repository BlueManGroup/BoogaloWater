package com.example.myloginapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myloginapp.R;
import com.example.myloginapp.models.LogsModel;

import java.util.ArrayList;

public class LogAdapter extends RecyclerView.Adapter<com.example.myloginapp.adapters.LogAdapter.MyViewHolder> {



    Context context;
    ArrayList<LogsModel> logModels;

    public LogAdapter(Context context, ArrayList<LogsModel> logModels) {
        this.context = context;
        this.logModels = logModels;
    }

    @NonNull
    @Override
    public com.example.myloginapp.adapters.LogAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // This method inflates the layout, i.e. gives it its look.
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_logs_user_role, parent, false);

        return new com.example.myloginapp.adapters.LogAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.myloginapp.adapters.LogAdapter.MyViewHolder holder, int position) {
        // This method assigns values to the views in the recycler_view_row layout file,
        // based on the position of the recycler view.
        holder.textView.setText(logModels.get(position).getUsername());
        holder.button.setText(logModels.get(position).getRole());

        // Below set the profile picture. Ignore for now
        // holder.imageView.setImageResource(userRightsModels.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        // This method tells the recyclerView how many items it has to display.
        return logModels.size();
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

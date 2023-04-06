package com.example.myloginapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class URP_RecyclerViewAdapter extends RecyclerView.Adapter<URP_RecyclerViewAdapter.MyViewHolder> {
    Context context;
    ArrayList<UserRightsModel> userRightsModels;

    public URP_RecyclerViewAdapter(Context context, ArrayList<UserRightsModel> userRightsModels) {
        this.context = context;
        this.userRightsModels = userRightsModels;
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
        // This method assigns values to the views in the recycler_view_row layout file,
        // based on the position of the recycler view.
        holder.textView.setText(userRightsModels.get(position).getUsername());
        holder.button.setText(userRightsModels.get(position).getRole());

        // Below set the profile picture. Ignore for now
        // holder.imageView.setImageResource(userRightsModels.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        // This method tells the recyclerView how many items it has to display.
        return userRightsModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //ImageView imageView; // Will eventually hold the profile picture.
        TextView textView;
        Button button;
        View.OnClickListener listener;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            //imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
            button = itemView.findViewById(R.id.button);
            button.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            //listener.onButtonClick();
        }
    }
}

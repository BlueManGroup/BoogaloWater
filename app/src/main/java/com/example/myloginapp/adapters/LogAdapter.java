package com.example.myloginapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myloginapp.R;
import com.example.myloginapp.models.LogsModel;

import java.util.ArrayList;

public class LogAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;

    public LogAdapter(Context context, ArrayList<LogsModel> logsModels) {
        this.context = context;
        this.logsModels = logsModels;
        System.out.println("constructor");
    }

    ArrayList<LogsModel> logsModels;

    private static final int VIEW_TYPE_ONE = 1;
    private static final int VIEW_TYPE_TWO = 2;
    private static final int VIEW_TYPE_THREE = 3;


    @Override
    public int getItemViewType(int position) {
        // Determine the view type based on the "action" property of the object at this position
        LogsModel logObj = logsModels.get(position);
        String action = logObj.getAction();
        System.out.println("getItemViewType");
        if (action.equals("change user rights")) {
            return VIEW_TYPE_ONE;
        } else if (action.equals("distribute")) {
            return VIEW_TYPE_TWO;
        } else if (action.equals("redeem")) {
            return VIEW_TYPE_THREE;
        } else {
            return -1;
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the appropriate layout based on the view type
        System.out.println("onCreateViewHolder");
        switch (viewType) {
            case VIEW_TYPE_ONE:
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_logs_user_role, parent, false);
                return new ChangeUserRightsViewHolder(view);
            case VIEW_TYPE_TWO:
                View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_logs_dist, parent, false);
                return new DistributeViewHolder(view2);
            case VIEW_TYPE_THREE:
                View view3 = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_logs_redeem, parent, false);
                return new RedeemViewHolder(view3);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        LogsModel logObj = logsModels.get(position);
        System.out.println("onBindViewHolder");
        System.out.println(holder.getItemViewType());
        switch (holder.getItemViewType()) {
            case VIEW_TYPE_ONE:
                System.out.println("case1");
                ChangeUserRightsViewHolder changeUserRightsViewHolder = (ChangeUserRightsViewHolder) holder;
                changeUserRightsViewHolder.bind(logObj);
                break;
            case VIEW_TYPE_TWO:
                System.out.println("case2");
                DistributeViewHolder distributeViewHolder = (DistributeViewHolder) holder;
                distributeViewHolder.bind(logObj);
                break;
            case VIEW_TYPE_THREE:
                System.out.println("case3");
                RedeemViewHolder redeemViewHolder = (RedeemViewHolder) holder;
                redeemViewHolder.bind(logObj);
                break;
        }


    }


    @Override
    public int getItemCount() {
        /*System.out.println("getItemCount")*/;
        return logsModels.size();
    }

    private static class ChangeUserRightsViewHolder extends RecyclerView.ViewHolder {
        // Define the views for this view holder

        TextView user;
        TextView action;
        TextView date;
        TextView receiverField;
        TextView roleField;
        public ChangeUserRightsViewHolder(View itemView) {
            super(itemView);

            user = itemView.findViewById(R.id.logInitiator);
            action = itemView.findViewById(R.id.logAction);
            date = itemView.findViewById(R.id.logDate);
            receiverField = itemView.findViewById(R.id.logReceiverField);
            roleField = itemView.findViewById(R.id.logRoleField);
            System.out.println("ChangeUserRightViewHolder Constructor");
        }

        public void bind(LogsModel logObj) {
            user.setText(logObj.getInitiator());
            action.setText(logObj.getAction());
            date.setText(logObj.getDate());
            receiverField.setText(logObj.getReceiver());
            roleField.setText(logObj.getRole());

            System.out.println("ChangeUserRightViewHolder Bind");

        }

    }

    private static class DistributeViewHolder extends RecyclerView.ViewHolder {
        TextView user;
        TextView action;
        TextView date;
        TextView receiverField;
        TextView tokensCount;


        public DistributeViewHolder(View itemView) {
            super(itemView);

            user = itemView.findViewById(R.id.logInitiator);
            action = itemView.findViewById(R.id.logAction);
            date = itemView.findViewById(R.id.logDate);
            receiverField= itemView.findViewById(R.id.logReceiverField);
            tokensCount = itemView.findViewById(R.id.logTokensCount);

            System.out.println("Distribute Constructor");


            /*tokensInt = itemView.findViewById(R.id.logTokensInt);*/
        }

        public void bind(LogsModel logObj) {
            user.setText(logObj.getInitiator());
            action.setText(logObj.getAction());
            date.setText(logObj.getDate());
            receiverField.setText(logObj.getReceiver());
            tokensCount.setText(logObj.getTokensCount());
            System.out.println("Distribute Bind");

        }
    }

    private static class RedeemViewHolder extends RecyclerView.ViewHolder {
        // Define the views for this view holder
        TextView user;
        TextView action;
        TextView date;

        public RedeemViewHolder(View itemView) {
            super(itemView);

            user = itemView.findViewById(R.id.logInitiator);
            action = itemView.findViewById(R.id.logAction);
            date = itemView.findViewById(R.id.logDate);

            /*System.out.println("ChangeUserRightRedeem Constructor");*/

        }

        public void bind(LogsModel logObj) {
            user.setText(logObj.getInitiator());
            action.setText(logObj.getAction());
            date.setText(logObj.getDate());

            System.out.println("ChangeUserRightRedeem Bind");

        }
    }
}


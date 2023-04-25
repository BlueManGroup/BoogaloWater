package com.example.myloginapp.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

public class LogsModel {

    String isoDate;
    String action;
    String initiator;
    String receiver;
    String role;
    Integer tokensCount;


    public LogsModel(Map<String, Object> data) {

        Map<String, Object> userObj = (Map<String, Object>) data.get("userObj");
        Map<String, Object> tokenObj = (Map<String, Object>) data.getOrDefault("tokens", null);

        this.isoDate = (String) data.getOrDefault("date", null);
        this.action = (String) data.getOrDefault("action", null);
        this.initiator = (String) userObj.getOrDefault("initiator", null);
        this.receiver = (String) userObj.getOrDefault("receiver", null);
        this.role = (String) data.getOrDefault("role", null);

        System.out.println(tokenObj);
        if (tokenObj != null) {
            Object tokenCountRaw = tokenObj.getOrDefault("tokenAmount", null);
            Double tokenCountDouble;
            if (tokenCountRaw != null) {
                tokenCountDouble = (Double) tokenCountRaw;
                this.tokensCount = tokenCountDouble.intValue();
            } else {
                this.tokensCount = null;
            }
        }



    }


    public String getDate() {
        SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        inputDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        SimpleDateFormat outputDateFormat = new SimpleDateFormat("MMM dd, yyyy hh:mm a");
        outputDateFormat.setTimeZone(TimeZone.getDefault());

        try {
            Date date = inputDateFormat.parse(isoDate);
            String formattedDate = outputDateFormat.format(date);
            return formattedDate;
        } catch (ParseException e) {
            e.printStackTrace();
            return "error";
        }
    }

    public String getAction() {
        return action;
    }

    public String getInitiator() {
        return initiator;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getRole() {
        return role;
    }

    public String getTokensCount() {
        return tokensCount.toString();
    }










}



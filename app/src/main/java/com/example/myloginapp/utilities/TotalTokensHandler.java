package com.example.myloginapp.utilities;


import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;
import com.example.myloginapp.models.ReqObj;
public class TotalTokensHandler {

    public static TextView totalTokensTextview;

    public TotalTokensHandler(TextView totalTokensTextview) {
        this.totalTokensTextview = totalTokensTextview;
    }

    public TotalTokensHandler() {

    }

     public void setTotalTokensText(TokenManager tokenManager) {
         try {
            String token = tokenManager.getJwtToken().toString();
            Map<String, String> data = new HashMap<String, String>();
            data.put("token", token);

            ReqObj reqObj = new ReqObj(data);
            Future<Object> res = RequestHandler.postJson(reqObj, "tokens/count");

            //getting user total token amount

            Map<String, Double> resMap = (Map<String, Double>) res.get();
            int amount = resMap.get("response").intValue();

            totalTokensTextview.setText(amount + " tokens");

        } catch(Exception e) {
            System.out.println(e);
        }
     }
}

package com.example.myloginapp;
import androidx.annotation.NonNull;

import com.google.gson.Gson;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class RequestHandler {
    private static String url = "";
    public static Object resObj = null;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final OkHttpClient client = new OkHttpClient();

    public static boolean postJson(ReqObj object, String sublink) {
        System.out.println(object);
        url = "http://10.92.1.215/" + sublink;
        RequestBody body = RequestBody.create(object.toString(), JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        System.out.println(request);

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull okhttp3.Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                    Gson gson = new Gson();
                    resObj = gson.fromJson(responseBody, Object.class);
                    System.out.println(resObj);
                } else {
                    // Handle unsuccessful response gracefully here
                    System.out.println("Too bad dude, gg");

                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
        });
        return false;
    }
}




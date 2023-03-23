package com.example.myloginapp;
import androidx.annotation.NonNull;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.concurrent.Future;
import java.util.concurrent.CompletableFuture;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class RequestHandler {
    private static String url;
    public static Object resObj = null;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final OkHttpClient client = new OkHttpClient();
    public static boolean isSuccess;

    public static Future<Object> postJson(ReqObj object, String sublink) {
        isSuccess = false;
        url = "http://10.92.1.215/" + sublink;
        RequestBody body = RequestBody.create(object.toString(), JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        CompletableFuture<Object> f = new CompletableFuture<>();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull okhttp3.Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                    Gson gson = new Gson();
                    resObj = gson.fromJson(responseBody, Object.class);
                    f.complete(resObj);
                } else {
                    // Handle unsuccessful response gracefully here
                    System.out.println("Too bad dude, gg");

                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                f.completeExceptionally(e);
            }
        });
        return f;
    }
}




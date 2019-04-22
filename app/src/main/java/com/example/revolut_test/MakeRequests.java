package com.example.revolut_test;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MakeRequests{

    private String URL = "https://revolut.duckdns.org/latest?base=EUR";
    private OkHttpClient client;
    private JSONObject jsonArray;
    private ArrayList<Currency> currencies;

    public ArrayList<Currency> asyncrequest(){
        client = new OkHttpClient();
        currencies = new ArrayList<>();
        Request request = new Request.Builder()
                .url(URL)
                .build();

        CountDownLatch  countDownLatch = new CountDownLatch(1);

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("app","Something went wrong" + e.getMessage());
                countDownLatch.countDown();
            }

            @Override
            public void onResponse(Call call, final Response response)  {
                JSONObject jobject = null;
                if (response.isSuccessful()) {
                    try {
                        jobject= new JSONObject(Objects.requireNonNull(response.body()).string());
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                        jobject = null;
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (jobject != null) {
                        try {
                            jsonArray = jobject.getJSONObject("rates");
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if (jsonArray != null) {
                            Iterator x = jsonArray.keys();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                String key = x.next().toString();
                                Currency temp;
                                try {
                                    temp = new Currency(key,jsonArray.getString(key));
                                    currencies.add(temp);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                x.hasNext();
                            }
                        }
                    }
                }
                countDownLatch.countDown();
            }
        });

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (currencies.size() != 0)
            return currencies;
        else
            return null;
    }

}
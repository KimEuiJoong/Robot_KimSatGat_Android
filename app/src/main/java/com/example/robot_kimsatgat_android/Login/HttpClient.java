package com.example.robot_kimsatgat_android.Login;

import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpClient {

    private static Retrofit retrofit;
    private static OkHttpClient client;

    public static Retrofit getRetrofit(){
        if(client == null){
            client = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor()).build();
        }
        if(retrofit == null){
            Retrofit.Builder builder = new Retrofit.Builder();
            builder.baseUrl("https://rest.robotkimsatgat.p-e.kr");
            //builder.baseUrl("https://10.0.2.2:8000");
            builder.addConverterFactory(GsonConverterFactory.create());
            builder.client(client);

            retrofit = builder.build();
        }
        return retrofit;
    }
    private static HttpLoggingInterceptor httpLoggingInterceptor(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.w("http_log:",message);
            }
        });
        return interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    }
}

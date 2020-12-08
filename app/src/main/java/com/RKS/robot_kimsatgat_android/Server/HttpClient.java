package com.RKS.robot_kimsatgat_android.Server;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpClient {

    private static Retrofit retrofit;
    private static OkHttpClient client;
    public static String token = "";

    public static void setToken(String t){token = t;}
    public static Retrofit getRetrofit(){
        if(client == null){
            client = new OkHttpClient.Builder()
                    .addInterceptor(tokenInterceptor())
                    .addInterceptor(httpLoggingInterceptor())
                    .build();
        }
        if(retrofit == null){
            Retrofit.Builder builder = new Retrofit.Builder();
            builder.baseUrl("https://rest.robotkimsatgat.p-e.kr");
            builder.addConverterFactory(GsonConverterFactory.create());
            builder.client(client);

            retrofit = builder.build();
        }
        return retrofit;
    }
    private static Interceptor tokenInterceptor(){
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder()
                        .addHeader("Authorization",token)
                        .build();
                return chain.proceed(request);
            }
        };
        return interceptor;
    }
    private static HttpLoggingInterceptor httpLoggingInterceptor(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i("http_log:",message);
            }
        });
        return interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    }

}

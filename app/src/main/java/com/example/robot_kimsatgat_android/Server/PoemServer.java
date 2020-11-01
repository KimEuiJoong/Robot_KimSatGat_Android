package com.example.robot_kimsatgat_android.Server;

import android.util.Log;

import com.example.robot_kimsatgat_android.Server.ParamClasses.RecvLoginData;
import com.example.robot_kimsatgat_android.Server.ParamClasses.RecvPoemData;
import com.example.robot_kimsatgat_android.Server.ParamClasses.ReqLoginData;
import com.example.robot_kimsatgat_android.Server.ParamClasses.ReqPoemData;

import kotlin.jvm.functions.Function1;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PoemServer {
    private static final String TAG = "PoemServer";
    ApiInterface api;
    private static PoemServer poemServer;
    private PoemServer(){
        api = HttpClient.getRetrofit().create(ApiInterface.class);
    }
    public static PoemServer getPoemServer(){
        if(poemServer == null){
            poemServer = new PoemServer();
        }
        return poemServer;
    }
    public static void setToken(String token){
        HttpClient.setToken(token);
    }

    public void postPoem(String title,String content){
        ReqPoemData reqPoemData = new ReqPoemData(title,content);
        Call<Void> call = api.postPoem(reqPoemData);
        call.enqueue(new Callback<Void>(){
            @Override
            public void onResponse(Call<Void> call, Response<Void> response){
                Log.i(TAG, response.body().toString());
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t){
                Log.e(TAG,"PoetPoemFailed");
            }
        });
    }
    public void getPoem(int poem_id, Function1<RecvPoemData,Void> func){
        Call<RecvPoemData> call = api.getPoem(poem_id);
        call.enqueue(new Callback<RecvPoemData>(){
            @Override
            public void onResponse(Call<RecvPoemData> call, Response<RecvPoemData> response){
                Log.i(TAG, response.body().toString());
                func.invoke(response.body());
            }
            @Override
            public void onFailure(Call<RecvPoemData> call, Throwable t){
                Log.e(TAG,"PoetPoemFailed");
            }
        });
    }
    public void updatePoem(int poem_id){

    }
    public void deletePoem(int poem_id){

    }
    public void recommendPoem(){
        Call<RecvPoemData> call = api.recommendPoem();
        call.enqueue(new Callback<RecvPoemData>(){
            @Override
            public void onResponse(Call<RecvPoemData> call, Response<RecvPoemData> response){
                Log.i(TAG, response.body().toString());
            }
            @Override
            public void onFailure(Call<RecvPoemData> call, Throwable t){
                Log.e(TAG,"PoetPoemFailed");
            }
        });
    }
    public void Login(String idToken,String name){
        ReqLoginData reqLoginData = new ReqLoginData(idToken,name);
        Call<RecvLoginData> call = api.verifyLoginToken(reqLoginData);
        call.enqueue(new Callback<RecvLoginData>(){
            @Override
            public void onResponse(Call<RecvLoginData> call, Response<RecvLoginData> response){
                Log.i(TAG, "LoginRes: "+response.body().toString());
                String token = response.body().toString();
                setToken("Token "+token);
            }
            @Override
            public void onFailure(Call<RecvLoginData> call, Throwable t){
                Log.e(TAG,"onFailure");
            }
        });
    }
}

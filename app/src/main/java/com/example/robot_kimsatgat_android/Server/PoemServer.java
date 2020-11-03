package com.example.robot_kimsatgat_android.Server;

import android.util.Log;

import com.example.robot_kimsatgat_android.Server.ParamClasses.RecvLikeData;
import com.example.robot_kimsatgat_android.Server.ParamClasses.RecvPoemBriefData;
import com.example.robot_kimsatgat_android.Server.ParamClasses.ReqCommentData;
import com.example.robot_kimsatgat_android.Server.ParamClasses.RecvCommentData;
import com.example.robot_kimsatgat_android.Server.ParamClasses.RecvLoginData;
import com.example.robot_kimsatgat_android.Server.ParamClasses.RecvPoemData;
import com.example.robot_kimsatgat_android.Server.ParamClasses.ReqLoginData;
import com.example.robot_kimsatgat_android.Server.ParamClasses.ReqPoemData;

import java.util.ArrayList;
import java.util.List;

import kotlin.jvm.functions.Function0;
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

    public void postPoem(String title,String content){
        postPoem(title, content, new Function0<Void>() {
            @Override
            public Void invoke() {
                return null;
            }
        });
    }
    public void postPoem(String title,String content,Function0<Void> func){
        ReqPoemData reqPoemData = new ReqPoemData(title,content);
        Call<Void> call = api.postPoem(reqPoemData);
        call.enqueue(new Callback<Void>(){
            @Override
            public void onResponse(Call<Void> call, Response<Void> response){
                Log.i(TAG, "posted, Auth:"+HttpClient.token);
                func.invoke();
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
                Log.e(TAG,"getPoemFailed");
            }
        });
    }
    public void updatePoem(int poem_id){

    }
    public void deletePoem(int poem_id){

    }
    public void recommendPoem(Function1<RecvPoemData,Void> func){
        Call<RecvPoemData> call = api.recommendPoem();
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
    public void getMyPoemList(Function1<List<RecvPoemBriefData>,Void> func){
        Call<ArrayList<RecvPoemBriefData>> call = api.getMyPoemList();
        call.enqueue(new Callback<ArrayList<RecvPoemBriefData>>() {
            @Override
            public void onResponse(Call<ArrayList<RecvPoemBriefData>> call, Response<ArrayList<RecvPoemBriefData>> response) {
                func.invoke(response.body());
            }
            @Override
            public void onFailure(Call<ArrayList<RecvPoemBriefData>> call, Throwable t) {
                Log.e(TAG,"getMyPoemList Failed");
            }
        });
    }

    public void postComment(int poem_id, String content){
        postComment(poem_id, content, new Function0<Void>() {
            @Override
            public Void invoke() {
                return null;
            }
        });
    }
    public void postComment(int poem_id, String content, Function0<Void> func){
        ReqCommentData reqCommentData = new ReqCommentData(content);
        Call<Void> call = api.postComment(poem_id, reqCommentData);
        call.enqueue(new Callback<Void>(){
            @Override
            public void onResponse(Call<Void> call, Response<Void> response){
                func.invoke();
                Log.i(TAG, "posted, Auth:"+HttpClient.token);
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t){
                Log.e(TAG,"post comment failed");
            }
        });
    }
    public void getComments(int poem_id,Function1<List<RecvCommentData>,Void> func){
        Call<ArrayList<RecvCommentData>> call = api.getComments(poem_id);
        call.enqueue(new Callback<ArrayList<RecvCommentData>>() {
            @Override
            public void onResponse(Call<ArrayList<RecvCommentData>> call, Response<ArrayList<RecvCommentData>> response) {
                func.invoke(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<RecvCommentData>> call, Throwable t) {
                Log.e(TAG,"getCommentsFailed");
            }
        });
    }
    public void updateComment(int poem_id, int comment_id, String content){}
    public void deleteComment(int poem_id, int comment_id){}

    public void postLike(int poem_id){
        postLike(poem_id, new Function0<Void>() {
            @Override
            public Void invoke() {
                return null;
            }
        });
    }
    public void postLike(int poem_id,Function0<Void> func){
        Call<Void> call = api.postLike(poem_id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                func.invoke();
                Log.i(TAG, "posted like, Auth:"+HttpClient.token);
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e(TAG,"post like Failed");
            }
        });
    }
    public void getLike(int poem_id){
        getLike(poem_id, new Function1<RecvLikeData, Void>() {
            @Override
            public Void invoke(RecvLikeData recvLikeData) {
                return null;
            }
        });
    }
    public void getLike(int poem_id,Function1<RecvLikeData,Void> func){
        Call<RecvLikeData> call = api.getLike(poem_id);
        call.enqueue(new Callback<RecvLikeData>() {
            @Override
            public void onResponse(Call<RecvLikeData> call, Response<RecvLikeData> response) {
                func.invoke(response.body());
                Log.i(TAG, "got likenum, Auth:"+HttpClient.token);
            }
            @Override
            public void onFailure(Call<RecvLikeData> call, Throwable t) {
                Log.e(TAG, "getting likenum failed");
            }
        });
    }
    public void deleteLike(int poem_id){
        deleteLike(poem_id, new Function0<Void>() {
            @Override
            public Void invoke() {
                return null;
            }
        });
    }
    public void deleteLike(int poem_id,Function0<Void> func){
        Call<Void> call = api.deleteLike(poem_id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                func.invoke();
                Log.i(TAG, "deleted like, Auth:"+HttpClient.token);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e(TAG,"delete like Failed");
            }
        });
    }

    public void getMyLikeList(Function1<List<RecvPoemBriefData>,Void> func){
        Call<ArrayList<RecvPoemBriefData>> call = api.getMyLikeList();
        call.enqueue(new Callback<ArrayList<RecvPoemBriefData>>() {
            @Override
            public void onResponse(Call<ArrayList<RecvPoemBriefData>> call, Response<ArrayList<RecvPoemBriefData>> response) {
                func.invoke(response.body());
            }
            @Override
            public void onFailure(Call<ArrayList<RecvPoemBriefData>> call, Throwable t) {
                Log.e(TAG,"getMyLikeList Failed");
            }
        });
    }

}

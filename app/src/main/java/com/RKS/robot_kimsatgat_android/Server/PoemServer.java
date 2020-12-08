package com.RKS.robot_kimsatgat_android.Server;

import android.util.Log;

import com.RKS.robot_kimsatgat_android.Server.ParamClasses.RecvCommentData;
import com.RKS.robot_kimsatgat_android.Server.ParamClasses.RecvLikeData;
import com.RKS.robot_kimsatgat_android.Server.ParamClasses.RecvLoginData;
import com.RKS.robot_kimsatgat_android.Server.ParamClasses.RecvPoemBriefData;
import com.RKS.robot_kimsatgat_android.Server.ParamClasses.RecvPoemData;
import com.RKS.robot_kimsatgat_android.Server.ParamClasses.ReqCommentData;
import com.RKS.robot_kimsatgat_android.Server.ParamClasses.ReqLoginData;
import com.RKS.robot_kimsatgat_android.Server.ParamClasses.ReqPoemData;
import com.RKS.robot_kimsatgat_android.Server.ParamClasses.ReqSurveyData;
import com.RKS.robot_kimsatgat_android.ViewModels.SingleLiveEvent;

import java.util.ArrayList;
import java.util.HashMap;
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
    
    //
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

    // 로그인
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
    // 로그인
    public void Login(String idToken,String name,Function0<Void> func){
        ReqLoginData reqLoginData = new ReqLoginData(idToken,name);
        Call<RecvLoginData> call = api.verifyLoginToken(reqLoginData);
        call.enqueue(new Callback<RecvLoginData>(){
            @Override
            public void onResponse(Call<RecvLoginData> call, Response<RecvLoginData> response){
                Log.i(TAG, "LoginRes: "+response.body().toString());
                String token = response.body().toString();
                setToken("Token "+token);
                func.invoke();
            }
            @Override
            public void onFailure(Call<RecvLoginData> call, Throwable t){
                Log.e(TAG,"onFailure");
            }
        });
    }

    // 시 등록
    public void postPoem(String title,String content,ArrayList<String> tags){
        postPoem(title, content,tags, new Function0<Void>() {
            @Override
            public Void invoke() {
                return null;
            }
        });
    }
    public void postPoem(String title,String content,ArrayList<String> tags,Function0<Void> func){
        ReqPoemData reqPoemData = new ReqPoemData(title,content,tags);
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

    // 시 가져오기
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
    private HashMap<Integer,SingleLiveEvent<RecvPoemData>> poemDatas = new HashMap<>();
    public SingleLiveEvent<RecvPoemData> getPoem(int poem_id){
        if(!poemDatas.containsKey(poem_id)){
            poemDatas.put(poem_id,new SingleLiveEvent<>());
        }
        Call<RecvPoemData> call = api.getPoem(poem_id);
        call.enqueue(new Callback<RecvPoemData>(){
            @Override
            public void onResponse(Call<RecvPoemData> call, Response<RecvPoemData> response){
                poemDatas.get(poem_id).setValue(response.body());
            }
            @Override
            public void onFailure(Call<RecvPoemData> call, Throwable t){
                Log.e(TAG,"getPoemFailed");
            }
        });
        return poemDatas.get(poem_id);
    }
    // 시 수정
    public void updatePoem(int poem_id){

    }
    // 시 삭제
    public void deletePoem(int poem_id){

    }
    // 추천 시 가져오기
    public void recommendPoem(Function1<RecvPoemData,Void> func){
        Call<RecvPoemData> call = api.recommendPoem();
        call.enqueue(new Callback<RecvPoemData>(){
            @Override
            public void onResponse(Call<RecvPoemData> call, Response<RecvPoemData> response){
                //Log.i(TAG, response.body().toString());
                func.invoke(response.body());
            }
            @Override
            public void onFailure(Call<RecvPoemData> call, Throwable t){
                Log.e(TAG,"PoetPoemFailed");
            }
        });
    }
    private SingleLiveEvent<List<RecvPoemBriefData>> myRecommendList = new SingleLiveEvent<>();
    public SingleLiveEvent<List<RecvPoemBriefData>> getMyRecommendList(){
        Call<ArrayList<RecvPoemBriefData>> call = api.getMyRecommendList();
        call.enqueue(new Callback<ArrayList<RecvPoemBriefData>>() {
            @Override
            public void onResponse(Call<ArrayList<RecvPoemBriefData>> call, Response<ArrayList<RecvPoemBriefData>> response) {
                myRecommendList.setValue(response.body());
            }
            @Override
            public void onFailure(Call<ArrayList<RecvPoemBriefData>> call, Throwable t) {
                Log.e(TAG,"getMyPoemList Failed");
            }
        });
        return myRecommendList;
    }

    // 내가 쓴 시 가져오기
    private SingleLiveEvent<List<RecvPoemBriefData>> myPoemList = new SingleLiveEvent<>();
    public SingleLiveEvent<List<RecvPoemBriefData>> getMyPoemList(){
        Call<ArrayList<RecvPoemBriefData>> call = api.getMyPoemList();
        call.enqueue(new Callback<ArrayList<RecvPoemBriefData>>() {
            @Override
            public void onResponse(Call<ArrayList<RecvPoemBriefData>> call, Response<ArrayList<RecvPoemBriefData>> response) {
                myPoemList.setValue(response.body());
            }
            @Override
            public void onFailure(Call<ArrayList<RecvPoemBriefData>> call, Throwable t) {
                Log.e(TAG,"getMyPoemList Failed");
            }
        });
        return myPoemList;
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


    public void postSurvey(String feeling,Function0<Void> func){
        ReqSurveyData reqSurveyData = new ReqSurveyData(feeling);
        Call<Void> call = api.postSurvey(reqSurveyData);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.i(TAG, "posted survey, Auth:"+HttpClient.token);
                func.invoke();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.i(TAG, "posting survey failed, Auth:"+HttpClient.token);
            }
        });
    }
    public void postLikeFeedback(){
        postLikeFeedback(new Function0<Void>() {
            @Override
            public Void invoke() {
                return null;
            }
        });
    }
    public void postLikeFeedback(Function0<Void> func){
        Call<Void> call = api.postLikeFeedback();
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                func.invoke();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
    public void deleteLikeFeedback(){
        deleteLikeFeedback(new Function0<Void>() {
            @Override
            public Void invoke() {
                return null;
            }
        });
    }
    public void deleteLikeFeedback(Function0<Void> func){
        Call<Void> call = api.deleteLikeFeedback();
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                func.invoke();
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    // 댓글 달기
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

    // 댓글 가져오기
    private SingleLiveEvent<List<RecvCommentData>> myComments = new SingleLiveEvent<>();
    public SingleLiveEvent<List<RecvCommentData>> getComments(int poem_id){
        Call<ArrayList<RecvCommentData>> call = api.getComments(poem_id);
        call.enqueue(new Callback<ArrayList<RecvCommentData>>() {
            @Override
            public void onResponse(Call<ArrayList<RecvCommentData>> call, Response<ArrayList<RecvCommentData>> response) {
                myComments.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<RecvCommentData>> call, Throwable t) {
                Log.e(TAG,"getComments Failed");
            }

        });
        return myComments;
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

    // 좋아요 가져오기
    private HashMap<Integer,SingleLiveEvent<RecvLikeData>> likeDatas = new HashMap<>();
    public SingleLiveEvent<RecvLikeData> getLike(int poem_id){
        if(!likeDatas.containsKey(poem_id)){
            likeDatas.put(poem_id,new SingleLiveEvent<>());
        }
        Call<RecvLikeData> call = api.getLike(poem_id);
        call.enqueue(new Callback<RecvLikeData>() {
            @Override
            public void onResponse(Call<RecvLikeData> call, Response<RecvLikeData> response) {
                likeDatas.get(poem_id).setValue(response.body());
                Log.i(TAG, "got likenum, Auth:"+HttpClient.token);
            }
            @Override
            public void onFailure(Call<RecvLikeData> call, Throwable t) {
                Log.e(TAG, "getting likenum failed");
            }
        });
        return likeDatas.get(poem_id);
    }
    public void updateComment(int poem_id, int comment_id, String content){}
    public void deleteComment(int poem_id, int comment_id){}

    // 좋아요 누르기기
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

    private SingleLiveEvent<List<RecvPoemBriefData>> myLikeList = new SingleLiveEvent<>();
    public SingleLiveEvent<List<RecvPoemBriefData>> getMyLikeList(){
        Call<ArrayList<RecvPoemBriefData>> call = api.getMyLikeList();
        call.enqueue(new Callback<ArrayList<RecvPoemBriefData>>() {
            @Override
            public void onResponse(Call<ArrayList<RecvPoemBriefData>> call, Response<ArrayList<RecvPoemBriefData>> response) {
                myLikeList.setValue(response.body());
            }
            @Override
            public void onFailure(Call<ArrayList<RecvPoemBriefData>> call, Throwable t) {
                Log.e(TAG,"getMyLikeList Failed");
            }
        });
        return myLikeList;
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

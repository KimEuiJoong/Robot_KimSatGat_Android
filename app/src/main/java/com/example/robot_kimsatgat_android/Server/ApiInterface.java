package com.example.robot_kimsatgat_android.Server;

import com.example.robot_kimsatgat_android.Server.ParamClasses.RecvLoginData;
import com.example.robot_kimsatgat_android.Server.ParamClasses.RecvPoemData;
import com.example.robot_kimsatgat_android.Server.ParamClasses.ReqLoginData;
import com.example.robot_kimsatgat_android.Server.ParamClasses.ReqPoemData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiInterface {

    @POST("/logintoken")
    Call<RecvLoginData> verifyLoginToken(@Body ReqLoginData reqLoginData );   // @Body : request 파라미터

    @POST("/poems")
    Call<Void> postPoem(@Body ReqPoemData reqPoemData );   // @Body : request 파라미터

    @GET("/poems/{num}")
    Call<RecvPoemData> getPoem(@Path("num") int poem_id);   // @Body : request 파라미터

    @PUT("/poems/{num}")
    Call<Void> putPoem(@Path("num") int poem_id);   // @Body : request 파라미터

    @DELETE("/poems/{num}")
    Call<Void> deletePoem(@Path("num") int poem_id);   // @Body : request 파라미터

    @GET("/poems/recommended")
    Call<RecvPoemData> recommendPoem();   // @Body : request 파라미터
}
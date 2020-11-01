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
    Call<RecvLoginData> verifyLoginToken(@Body ReqLoginData reqLoginData );

    @POST("/poems")
    Call<Void> postPoem(@Body ReqPoemData reqPoemData );

    @GET("/poems/{num}")
    Call<RecvPoemData> getPoem(@Path("num") int poem_id);

    @PUT("/poems/{num}")
    Call<Void> putPoem(@Path("num") int poem_id,ReqPoemData reqPoemData);

    @DELETE("/poems/{num}")
    Call<Void> deletePoem(@Path("num") int poem_id);

    @GET("/poems/recommended")
    Call<RecvPoemData> recommendPoem();
}
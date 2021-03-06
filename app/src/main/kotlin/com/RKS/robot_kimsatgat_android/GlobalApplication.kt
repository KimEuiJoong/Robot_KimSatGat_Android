package com.RKS.robot_kimsatgat_android

import android.app.Application
import android.content.Context
import android.util.Log
import com.kakao.sdk.auth.LoginClient
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.user.UserApiClient

class GlobalApplication :Application(){
    val TAG = "RobotSatgat_GlobalApp"
    var accessToken = ""
    var name = ""

    override fun onCreate(){
        super.onCreate()
        KakaoSdk.init(this,getString(R.string.kakao_native_key))
    }

    fun kakaoLogin(context: Context,after_login:()->Void){
        Log.i(TAG,"kakao login start")
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            Log.i(TAG,"callback")
            if (error != null){
                Log.e(TAG,"로그인 실패",error)
            }
            else if (token != null){
                Log.i(TAG,"로그인 성공 ${token.accessToken}")
                UserApiClient.instance.me{ user, error->
                    if(error != null){
                        Log.e(TAG,"사용자 정보 요청 실패",error)
                    }
                    else if(user != null){
                        name = "${user.kakaoAccount?.profile?.nickname}"
                        Log.i(TAG,"사용자 정보 요청 성공 "+ name)
                        accessToken = token.accessToken
                        after_login()
                    }
                }
            }
        }
        if (LoginClient.instance.isKakaoTalkLoginAvailable(context)){
            LoginClient.instance.loginWithKakaoTalk(context, callback = callback)
        }else{
            LoginClient.instance.loginWithKakaoAccount(context, callback = callback)
        }
    }

}
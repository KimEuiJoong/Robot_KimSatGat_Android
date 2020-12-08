package com.RKS.robot_kimsatgat_android.UI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.RKS.robot_kimsatgat_android.GlobalApplication;
import com.RKS.robot_kimsatgat_android.R;
import com.RKS.robot_kimsatgat_android.SampleData.SharedPreferencesUtil;
import com.RKS.robot_kimsatgat_android.Server.PoemServer;

import kotlin.jvm.functions.Function0;

public class LoginActivity extends AppCompatActivity {

    Context mContext;
    String key;
    Boolean check;

    private static final String TAG = "RobotSatgat_Login";

    //TODO : 하루에 설문조사 한번씩만 받기.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        /* 질문 했는지 안했는지 체크 */
        mContext = getApplicationContext();
        SharedPreferencesUtil pref = new SharedPreferencesUtil(mContext);

        String todayDate;
        String latestDate;

        todayDate = pref.setKey();
        /*
        check 가 true 일때 : 오늘은 설문조사를 했다
        check 가 false 일때 : 오늘은 설문조사를 안했다
         */
        if(!pref.contains("checked")){
            Log.i(TAG,"no checked");
            pref.setSharedString("checked",todayDate);
            check = false;
        }else{
            latestDate = pref.getSharedString("checked");
            Log.i(TAG, "latest:"+latestDate);
            Log.i(TAG, "today:"+todayDate);
            if(latestDate.equals(todayDate)){
                check = true;
            }else{
                pref.setSharedString("checked",todayDate);
                check = false;
            }
        }
        ImageButton loginButton = (ImageButton) findViewById(R.id.LoginButton);
        /* 스플래시 화면 후 자동 로그인*/
        kakaoLogin();
        /* 로그인이 실패한다면 버튼을 띄워주는것이 맞을텐데, 구현에 대해 조금 생각해봐야할듯.*/
        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                try {
                    kakaoLogin();
                }catch(Exception e){
                    Toast.makeText(LoginActivity.this,"kakaoLoginError",Toast.LENGTH_SHORT).show();
                    Log.e(TAG,"kakaoLoginError");
                }

            }
        });
    }

    private void kakaoLogin(){

        GlobalApplication globalApplication = (GlobalApplication)getApplication();
        globalApplication.kakaoLogin(this, new Function0<Void>() {
            @Override
            public Void invoke() {
                Log.i(TAG,globalApplication.getAccessToken());
                PoemServer poemServer = PoemServer.getPoemServer();
                poemServer.Login(globalApplication.getAccessToken(), globalApplication.getName(), new Function0<Void>() {
                    @Override
                    public Void invoke() {
                        loginFinished();
                        return null;
                    }
                });
                return null;
            }
        });
    }

    private void loginFinished() {
        //update ui code here
        //login 과정이 끝나고 난 후 어느 activity로 이동할 것인지 여기에 logic 작성을 해주면 됨.
        //check : 오늘의 설문조사를 했는가?
        try{
            Log.i(TAG,check.toString());
            if (check == true) {
                Intent intent = new Intent (LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else if (check == false) {
                Intent intent = new Intent(LoginActivity.this, Questionnaire1.class);
                startActivity(intent);
                finish();
            }
        }catch(Exception e){
            Log.e(TAG,e.getMessage());
        }
        //Intent intent = new Intent(this, Questionnaire1.class);
        //startActivity(intent);
        //finish();
    }
}
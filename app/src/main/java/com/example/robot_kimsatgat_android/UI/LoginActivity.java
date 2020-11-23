package com.example.robot_kimsatgat_android.UI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.robot_kimsatgat_android.GlobalApplication;
import com.example.robot_kimsatgat_android.R;
import com.example.robot_kimsatgat_android.SampleData.SharedPreferencesUtil;
import com.example.robot_kimsatgat_android.Server.PoemServer;

import kotlin.jvm.functions.Function0;

public class LoginActivity extends AppCompatActivity {

    Context mContext;
    String key;
    Boolean check;

    private static final String TAG = "RobotSatgat_Login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        /* 질문 했는지 안했는지 체크 */
        mContext = getApplicationContext();
        SharedPreferencesUtil pref = new SharedPreferencesUtil(mContext);
        key = pref.setKey();
        check = pref.getSharedBoolean(key);
        //아래 있는 코드 처럼 트루면 메인으로 가고 아니면 질문으로 가게...

        /*
        if (check == true) {
            Intent intent = new Intent (LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else if (check == false) {
            Intent intent = new Intent(LoginActivity.this, Questionnaire1.class);
            startActivity(intent);
            finish();
        }
         */


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

        Intent intent = new Intent(this, Questionnaire1.class);
        startActivity(intent);
        finish();

        // 잠시
        GlobalApplication globalApplication = (GlobalApplication)getApplication();
        globalApplication.kakaoLogin(this, new Function0<Void>() {
            @Override
            public Void invoke() {
                Log.i(TAG,globalApplication.getAccessToken());
                PoemServer poemServer = PoemServer.getPoemServer();
                poemServer.Login(globalApplication.getAccessToken(),globalApplication.getName());
                loginFinished();
                return null;
            }
        });
    }

    private void loginFinished() { //update ui code here
        Intent intent = new Intent(this, Questionnaire1.class);
        startActivity(intent);
        finish();
    }
}
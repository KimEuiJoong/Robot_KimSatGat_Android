package com.example.robot_kimsatgat_android.UI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.robot_kimsatgat_android.GlobalApplication;
import com.example.robot_kimsatgat_android.R;
import com.example.robot_kimsatgat_android.Server.PoemServer;

import kotlin.jvm.functions.Function0;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "RobotSatgat_Login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        ImageButton loginButton = (ImageButton) findViewById(R.id.LoginButton);
        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                try {
                    kakaoLogin();
                }catch(Exception e){
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
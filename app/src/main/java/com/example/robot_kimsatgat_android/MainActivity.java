package com.example.robot_kimsatgat_android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.robot_kimsatgat_android.View.View_LikeList;
import com.example.robot_kimsatgat_android.View.View_Suggested_Poem;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // 여러 액티비티 한번에 종료하기
    public static Activity _Main_Activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        _Main_Activity = MainActivity.this;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_list);

        // 버튼 객체
        Button btn_rcm_list;
        Button btn_like_list;
        Button btn_close_menu;

        // 버튼 동작
        btn_rcm_list = findViewById(R.id.btn_rcm_list);
        btn_rcm_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, View_Suggested_Poem.class);
                startActivity(intent);
            }
        });

        btn_like_list = findViewById(R.id.btn_like_list);
        btn_like_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, View_LikeList.class);
                startActivity(intent);
            }
        });

        btn_close_menu = findViewById(R.id.btn_close_menu);
        btn_close_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}

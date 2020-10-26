package com.example.robot_kimsatgat_android.DB;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.robot_kimsatgat_android.Login.Login;
import com.example.robot_kimsatgat_android.Questionnaire.Questionnaire1;
import com.example.robot_kimsatgat_android.R;

public class Poem_Write extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poem_write);

        Button btn_poem_save= findViewById(R.id.btn_poem_save);
        btn_poem_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Poem_Write.this, "저장되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

    }


}

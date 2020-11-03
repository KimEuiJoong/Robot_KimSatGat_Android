package com.example.robot_kimsatgat_android.Questionnaire;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.example.robot_kimsatgat_android.MainActivity;
import com.example.robot_kimsatgat_android.R;

public class Questionnaire2 extends AppCompatActivity {

    public static Activity _Questionnaire2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_second);
        _Questionnaire2 = Questionnaire2.this;

        RadioGroup RadioGroup_Tag;
        Button submit_button;

        // 버튼 동작
        submit_button = findViewById(R.id.submit_button);
        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Questionnaire2.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}

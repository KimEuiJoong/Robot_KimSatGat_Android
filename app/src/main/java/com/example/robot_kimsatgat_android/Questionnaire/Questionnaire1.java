package com.example.robot_kimsatgat_android.Questionnaire;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import com.example.robot_kimsatgat_android.MainActivity;
import com.example.robot_kimsatgat_android.R;
import com.example.robot_kimsatgat_android.View.View_Suggested_Poem;

import androidx.appcompat.app.AppCompatActivity;

public class Questionnaire1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_first);

        RadioGroup RadioGroup_feeling;
        Button next_to_second_question_button;

        // 버튼 동작
        next_to_second_question_button = findViewById(R.id.next_to_second_question_button);
        next_to_second_question_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Questionnaire1.this, Questionnaire2.class);
                startActivity(intent);
                finish();
            }
        });

    }
}

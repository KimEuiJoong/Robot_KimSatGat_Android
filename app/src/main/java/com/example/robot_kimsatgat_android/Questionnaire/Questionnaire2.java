package com.example.robot_kimsatgat_android.Questionnaire;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import com.example.robot_kimsatgat_android.MainActivity;
import com.example.robot_kimsatgat_android.R;
import com.example.robot_kimsatgat_android.View.View_Suggested_Poem;

import androidx.appcompat.app.AppCompatActivity;

public class Questionnaire2 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_second);

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

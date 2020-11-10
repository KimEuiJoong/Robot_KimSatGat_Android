package com.example.robot_kimsatgat_android.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.robot_kimsatgat_android.R;

public class Questionnaire1 extends AppCompatActivity {

    int feelingcheck = 0;
    RadioGroup first_radioGroup;
    Button next_to_second_question_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_first);

        first_radioGroup = (RadioGroup) findViewById(R.id.RadioGroup_feeling);

        // 버튼 동작
        next_to_second_question_button = findViewById(R.id.next_to_second_question_button);
        next_to_second_question_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Questionnaire1.this, Questionnaire2.class);
                intent.putExtra("feelingcheck",feelingcheck);
                startActivity(intent);
                finish();
            }
        });
        first_radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.positive) {
                    Toast.makeText(Questionnaire1.this,"positive",Toast.LENGTH_SHORT).show();
                    feelingcheck = 0;
                } else if(checkedId==R.id.middle_way) {
                    Toast.makeText(Questionnaire1.this,"middle",Toast.LENGTH_SHORT).show();
                    feelingcheck = 1;
                } else if(checkedId==R.id.negative) {
                    Toast.makeText(Questionnaire1.this,"negative",Toast.LENGTH_SHORT).show();
                    feelingcheck = 2;
                }
            }
        });
    }
}

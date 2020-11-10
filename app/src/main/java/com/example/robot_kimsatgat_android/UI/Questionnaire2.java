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

public class Questionnaire2 extends AppCompatActivity {


    RadioGroup positive_radioGroup;
    RadioGroup middle_radioGroup;
    RadioGroup negative_radioGroup;
    Button submit_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_second);

        Intent intent = getIntent();
        int getfeel = intent.getExtras().getInt("feelingcheck");

        positive_radioGroup = (RadioGroup) findViewById(R.id.positive_Tag);
        middle_radioGroup = (RadioGroup) findViewById(R.id.middle_Tag);
        negative_radioGroup = (RadioGroup) findViewById(R.id.negative_Tag);

        if (getfeel == 0) {
            positive_radioGroup.setVisibility(View.VISIBLE);
            middle_radioGroup.setVisibility(View.INVISIBLE);
            negative_radioGroup.setVisibility(View.INVISIBLE);
        } else if (getfeel == 1) {
            positive_radioGroup.setVisibility(View.INVISIBLE);
            middle_radioGroup.setVisibility(View.VISIBLE);
            negative_radioGroup.setVisibility(View.INVISIBLE);
        } else if (getfeel == 2) {
            positive_radioGroup.setVisibility(View.INVISIBLE);
            middle_radioGroup.setVisibility(View.INVISIBLE);
            negative_radioGroup.setVisibility(View.VISIBLE);
        }

        // 버튼 동작
        submit_button = findViewById(R.id.submit_button);
        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Questionnaire2.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        positive_radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==R.id.positive_Tag_1) {
                    Toast.makeText(Questionnaire2.this,"기쁨",Toast.LENGTH_SHORT).show();
                } else if (checkedId==R.id.positive_Tag_2) {
                    Toast.makeText(Questionnaire2.this,"감사",Toast.LENGTH_SHORT).show();
                } else if (checkedId==R.id.positive_Tag_3) {
                    Toast.makeText(Questionnaire2.this,"사랑",Toast.LENGTH_SHORT).show();
                } else if (checkedId==R.id.positive_Tag_4) {
                    Toast.makeText(Questionnaire2.this,"반가움",Toast.LENGTH_SHORT).show();
                } else if (checkedId==R.id.positive_Tag_5) {
                    Toast.makeText(Questionnaire2.this,"순수",Toast.LENGTH_SHORT).show();
                }
            }
        });
        middle_radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==R.id.middle_Tag_1) {
                    Toast.makeText(Questionnaire2.this,"심심",Toast.LENGTH_SHORT).show();
                } else if (checkedId==R.id.middle_Tag_2) {
                    Toast.makeText(Questionnaire2.this,"그리움",Toast.LENGTH_SHORT).show();
                } else if (checkedId==R.id.middle_Tag_3) {
                    Toast.makeText(Questionnaire2.this,"귀찮음",Toast.LENGTH_SHORT).show();
                }
            }
        });
        negative_radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==R.id.negative_Tag_1) {
                    Toast.makeText(Questionnaire2.this,"슬픔",Toast.LENGTH_SHORT).show();
                } else if (checkedId==R.id.negative_Tag_2) {
                    Toast.makeText(Questionnaire2.this,"절망",Toast.LENGTH_SHORT).show();
                } else if (checkedId==R.id.negative_Tag_3) {
                    Toast.makeText(Questionnaire2.this,"짜증",Toast.LENGTH_SHORT).show();
                } else if (checkedId==R.id.negative_Tag_4) {
                    Toast.makeText(Questionnaire2.this,"걱정",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

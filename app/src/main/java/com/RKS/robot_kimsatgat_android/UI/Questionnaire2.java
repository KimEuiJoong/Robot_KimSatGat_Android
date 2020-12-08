package com.RKS.robot_kimsatgat_android.UI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.RKS.robot_kimsatgat_android.R;
import com.RKS.robot_kimsatgat_android.Server.PoemServer;

import kotlin.jvm.functions.Function0;
import com.RKS.robot_kimsatgat_android.SampleData.SharedPreferencesUtil;

public class Questionnaire2 extends AppCompatActivity {

    Context mContext;
    RadioGroup positive_radioGroup;
    RadioGroup middle_radioGroup;
    RadioGroup negative_radioGroup;
    Button submit_button;
    String surveyResult;
    PoemServer poemServer;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_second);
        poemServer = PoemServer.getPoemServer();
        surveyResult = "";

        mContext = getApplicationContext();
        SharedPreferencesUtil pref = new SharedPreferencesUtil(mContext);
        key = pref.setKey();

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
                if(surveyResult != null) {
                    poemServer.postSurvey(surveyResult, new Function0<Void>() {
                        @Override
                        public Void invoke() {
                            Intent intent = new Intent(Questionnaire2.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                            return null;
                        }
                    });
                }else{
                    Toast.makeText(Questionnaire2.this,"적어도 하나는 골라주세요",Toast.LENGTH_SHORT).show();
                }
                //질문 했는지 체크하는 변수 true로 교체
                pref.setSharedBoolean(key);

                Intent intent = new Intent(Questionnaire2.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        positive_radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==R.id.positive_Tag_1) {
                    //Toast.makeText(Questionnaire2.this,"기쁨",Toast.LENGTH_SHORT).show();
                    surveyResult = "기쁨";
                } else if (checkedId==R.id.positive_Tag_2) {
                    //Toast.makeText(Questionnaire2.this,"감사",Toast.LENGTH_SHORT).show();
                    surveyResult = "감사";
                } else if (checkedId==R.id.positive_Tag_3) {
                    //Toast.makeText(Questionnaire2.this,"사랑",Toast.LENGTH_SHORT).show();
                    surveyResult = "사랑";
                } else if (checkedId==R.id.positive_Tag_4) {
                    //Toast.makeText(Questionnaire2.this,"반가움",Toast.LENGTH_SHORT).show();
                    surveyResult = "반가움";
                } else if (checkedId==R.id.positive_Tag_5) {
                    //Toast.makeText(Questionnaire2.this,"순수",Toast.LENGTH_SHORT).show();
                    surveyResult = "순수";
                }
            }
        });
        middle_radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==R.id.middle_Tag_1) {
                    //Toast.makeText(Questionnaire2.this,"심심",Toast.LENGTH_SHORT).show();
                    surveyResult = "심심";
                } else if (checkedId==R.id.middle_Tag_2) {
                    //Toast.makeText(Questionnaire2.this,"그리움",Toast.LENGTH_SHORT).show();
                    surveyResult = "그리움";
                } else if (checkedId==R.id.middle_Tag_3) {
                    //Toast.makeText(Questionnaire2.this,"귀찮음",Toast.LENGTH_SHORT).show();
                    surveyResult = "귀찮음";
                }
            }
        });
        negative_radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==R.id.negative_Tag_1) {
                    //Toast.makeText(Questionnaire2.this,"슬픔",Toast.LENGTH_SHORT).show();
                    surveyResult = "슬픔";
                } else if (checkedId==R.id.negative_Tag_2) {
                    //Toast.makeText(Questionnaire2.this,"절망",Toast.LENGTH_SHORT).show();
                    surveyResult = "절망";
                } else if (checkedId==R.id.negative_Tag_3) {
                    //Toast.makeText(Questionnaire2.this,"짜증",Toast.LENGTH_SHORT).show();
                    surveyResult = "짜증";
                } else if (checkedId==R.id.negative_Tag_4) {
                    //Toast.makeText(Questionnaire2.this,"걱정",Toast.LENGTH_SHORT).show();
                    surveyResult = "걱정";
                }
            }
        });
    }
}

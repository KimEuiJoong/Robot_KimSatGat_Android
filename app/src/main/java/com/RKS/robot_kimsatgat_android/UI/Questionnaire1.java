package com.RKS.robot_kimsatgat_android.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.RKS.robot_kimsatgat_android.R;

public class Questionnaire1 extends AppCompatActivity {

    int feelingcheck = -1;
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
                if (feelingcheck != -1){
                    Intent intent = new Intent(Questionnaire1.this, Questionnaire2.class);
                    intent.putExtra("feelingcheck",feelingcheck);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(Questionnaire1.this,"적어도 하나는 선택해주세요",Toast.LENGTH_SHORT).show();
                }
            }
        });
        first_radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                /*
                    이거 토스트 꼭 띄워줘야됨?
                 */
                if(checkedId==R.id.positive) {
                    //Toast.makeText(Questionnaire1.this,"긍정적",Toast.LENGTH_SHORT).show();
                    feelingcheck = 0;
                } else if(checkedId==R.id.middle_way) {
                    //Toast.makeText(Questionnaire1.this,"중립적",Toast.LENGTH_SHORT).show();
                    feelingcheck = 1;
                } else if(checkedId==R.id.negative) {
                    //Toast.makeText(Questionnaire1.this,"부정적",Toast.LENGTH_SHORT).show();
                    feelingcheck = 2;
                }
            }
        });
    }
}

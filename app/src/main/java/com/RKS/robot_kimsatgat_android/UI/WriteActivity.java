package com.RKS.robot_kimsatgat_android.UI;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.RKS.robot_kimsatgat_android.R;
import com.RKS.robot_kimsatgat_android.Server.PoemServer;

import java.util.ArrayList;

public class WriteActivity extends AppCompatActivity {
    private static final String[] tagList = {
            "기쁨"   ,
            "즐거움" ,
            "사랑1"  ,
            "사랑2"  ,
            "희망"   ,
            "활기"   ,
            "순수"   ,
            "잔잔"   ,
            "슬픔"   ,
            "의지"   ,
            "그리움1",
            "그리움2",
            "무심"   ,
            "고독"   ,
            "불안"   ,
            "성찰"
    };
    private int tag_checked_num = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poem_write);
        PoemServer poemServer = PoemServer.getPoemServer();

        Button btn_poem_save= findViewById(R.id.btn_poem_save);
        TextView editTitle = findViewById(R.id.editText_edit_title);
        TextView editContent = findViewById(R.id.editText_edit_content);
        ArrayList<CheckBox> cbList = new ArrayList<>();
        for(int i = 0;i<16;i++){
            int cbid = getResources().getIdentifier("checkBox"+Integer.toString(i+1),"id",this.getPackageName());
            Log.i("wrac",Integer.toString(cbid));
            CheckBox cb = findViewById(cbid);
            if(cb == null) Log.i("wrac","cbnull");
            cb.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    tag_checked_num = 0;
                    for(int i = 0;i<16;i++){
                        if(cbList.get(i).isChecked()){
                            tag_checked_num++;
                        }
                    }
                    if(tag_checked_num >= 3){
                        for(CheckBox cbi : cbList){
                            if(!cbi.isChecked()){
                                cbi.setEnabled(false);
                            }
                        }
                    }else{
                        for(CheckBox cbi : cbList){
                            cbi.setEnabled(true);
                        }
                    }
                }
            });
            cbList.add(cb);
        }
        btn_poem_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> checkedTags = new ArrayList<>();
                if(tag_checked_num < 3){
                    Toast.makeText(WriteActivity.this, "태그를 3개 골라주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }
                for(int i = 0;i<16;i++){
                    if(cbList.get(i).isChecked()){
                        checkedTags.add(tagList[i]);
                    }
                }
                poemServer.postPoem(editTitle.getText().toString(),editContent.getText().toString(),checkedTags);
                Toast.makeText(WriteActivity.this, "시를 작성하였습니다.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}

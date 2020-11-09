package com.example.robot_kimsatgat_android.UI;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.robot_kimsatgat_android.R;
import com.example.robot_kimsatgat_android.Server.PoemServer;

public class WriteActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poem_write);
        PoemServer poemServer = PoemServer.getPoemServer();

        Button btn_poem_save= findViewById(R.id.btn_poem_save);
        TextView editTitle = findViewById(R.id.editText_edit_title);
        TextView editContent = findViewById(R.id.editText_edit_content);
        btn_poem_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                poemServer.postPoem(editTitle.getText().toString(),editContent.getText().toString());
                Toast.makeText(WriteActivity.this, "저장되었습니다.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }


}

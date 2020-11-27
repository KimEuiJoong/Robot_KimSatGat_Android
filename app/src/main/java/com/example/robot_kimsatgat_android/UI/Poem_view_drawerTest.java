package com.example.robot_kimsatgat_android.UI;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.robot_kimsatgat_android.SampleData.Comment;
import com.example.robot_kimsatgat_android.SampleData.Comment_Adapter;

public class Poem_view_drawerTest extends AppCompatActivity {
    RecyclerView comment_recycler_view;
    Comment_Adapter comment_adapter;
    TextView name;
    TextView writer;
    TextView main;

    Comment comment1;
    Comment comment2;
    Comment comment3;
    String[] commentArray = new String[3];

    @Override
    protected void onCreate(Bundle savedInstaceState) {
        super.onCreate(savedInstaceState);
        /*
        setContentView(R.layout.poem_view);

        commentArray[0] = "테스트 1";
        commentArray[1] = "테스트 2";
        commentArray[2] = "테스트 3";
        comment1 = new Comment(1,commentArray[0]);
        comment1 = new Comment(2,commentArray[1]);
        comment1 = new Comment(3,commentArray[2]);


        //comment1.setComment(commentArray[0]);
        //comment2.setComment(commentArray[1]);
        //comment3.setComment(commentArray[2]);

        name = findViewById(R.id.comment_content);
        writer = findViewById(R.id.comment_writer);
        main = findViewById(R.id.poem_main);

        comment_recycler_view = findViewById(R.id.comment_recyclerView);
        GridLayoutManager layoutManager = new GridLayoutManager(this,1);
        comment_recycler_view.setLayoutManager(layoutManager);

        comment_adapter = new Comment_Adapter();

        comment_adapter.setHasStableIds(true);
        comment_adapter.addComment(comment1);
        comment_adapter.addComment(comment2);
        comment_adapter.addComment(comment3);

        comment_recycler_view.setAdapter(comment_adapter);

        name.setText("테스트");
        writer.setText("테스트");
        main.setText("테스트");

         */
    }
}
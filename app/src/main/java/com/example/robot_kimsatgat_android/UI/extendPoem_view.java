package com.example.robot_kimsatgat_android.UI;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.robot_kimsatgat_android.R;

public class extendPoem_view extends Poem_view {
    EditText comment_edit;
    RecyclerView comment_view;

    public extendPoem_view(Context context) {
        super(context);
        init(context);
    }
    public extendPoem_view(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.extend_poem_view,this,true);
        poem_title_view = findViewById(R.id.poem_title);
        poem_writer_view = findViewById(R.id.poem_writer);
        poem_main_view = findViewById(R.id.poem_main);

        poem_likenum_view = findViewById(R.id.like_count);
        Ibtn_poemlike = findViewById(R.id.likeIButton);

        comment_edit =findViewById(R.id.comment_edit);
        Ibtn_commentsend =findViewById(R.id.comment_send);
        comment_view =findViewById(R.id.comment_recyclerView);
    }

    public void getComment(String comment){}
    public void postComment(String comment){}

}

package com.example.robot_kimsatgat_android.UI;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.robot_kimsatgat_android.R;
import com.example.robot_kimsatgat_android.Server.PoemServer;

public class Poem_view extends LinearLayout {

    boolean like = false;
    Integer poem_id;
    String poem_title;
    String poem_writer;
    Integer poem_likenum;
    TextView poem_title_view;
    TextView poem_writer_view;
    TextView poem_main_view;
    TextView poem_likenum_view;
    ImageButton Ibtn_poemlike;
    PoemServer poemServer;
    ImageButton comment_btn = (ImageButton) findViewById(R.id.comment_send);
    View view;


    public Poem_view(Context context) {
        super(context);
        init(context);
    }

    public Poem_view(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.poem_view,this,true);
        poemServer = PoemServer.getPoemServer();
        poem_title_view = findViewById(R.id.poem_title);
        poem_writer_view = findViewById(R.id.poem_writer);
        poem_main_view = findViewById(R.id.poem_main);
        poem_likenum_view = findViewById(R.id.like_count);
        Ibtn_poemlike = findViewById(R.id.likeIButton);
    }

    public void setPoem_title(String title) {poem_title_view.setText(title);}
    public void setPoem_writer(String writer) {poem_writer_view.setText(writer);}
    public void setPoem_main_view(String main) {poem_main_view.setText(main);}
    public void setPoem_likenum(String likenum){poem_likenum_view.setText(likenum);}

}

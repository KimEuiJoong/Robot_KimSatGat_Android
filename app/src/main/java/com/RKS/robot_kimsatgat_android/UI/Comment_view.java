package com.RKS.robot_kimsatgat_android.UI;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.RKS.robot_kimsatgat_android.R;

public class Comment_view extends LinearLayout {

    TextView writer_TV;
    TextView content_TV;
    View view;

    public Comment_view(Context context) {
        super(context);
        init(context);
    }
    public Comment_view(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.comment_view,this,true);
        writer_TV = findViewById(R.id.poem_writer);
        content_TV = findViewById(R.id.poem_title);

    }

    public void setWriter(String writer) {writer_TV.setText(writer);}
    public void setContent(String content) {content_TV.setText(content);}
}

package com.example.robot_kimsatgat_android.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.robot_kimsatgat_android.R;

public class Poem_view extends LinearLayout implements View.OnClickListener{

    boolean like = false;
    TextView poem_title_view;
    TextView poem_writer_view;
    TextView poem_main_view;

    Button heart_btn= (Button) findViewById(R.id.like_button);
    Button heart_filled_btn = (Button) findViewById(R.id.not_like_button);

    ImageButton comment_btn = (ImageButton) findViewById(R.id.comment_send);

    @Override
    public void onClick(View v)
    {
        if (v == heart_btn) {
            heart_btn.setVisibility(View.INVISIBLE);
            heart_filled_btn.setVisibility(View.VISIBLE);
            like = true;
        } else if (v == heart_filled_btn) {
            heart_filled_btn.setVisibility(View.INVISIBLE);
            heart_btn.setVisibility(View.VISIBLE);
            like = false;
        }
    }

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
        inflater.inflate(R.layout.poem_view,this,true);

        poem_title_view = findViewById(R.id.poem_title);
        poem_writer_view = findViewById(R.id.poem_writer);
        poem_main_view = findViewById(R.id.poem_main);
    }

    public void setPoem_title(String title) {poem_title_view.setText(title);}
    public void setPoem_writer(String writer) {poem_writer_view.setText(writer);}
    public void setPoem_main_view(String main) {poem_main_view.setText(main);}

}

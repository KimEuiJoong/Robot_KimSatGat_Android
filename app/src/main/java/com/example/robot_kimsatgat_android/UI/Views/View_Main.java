package com.example.robot_kimsatgat_android.UI.Views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.robot_kimsatgat_android.R;
import com.example.robot_kimsatgat_android.SampleData.Comment;
import com.example.robot_kimsatgat_android.SampleData.Poem;
import com.example.robot_kimsatgat_android.Server.ParamClasses.RecvCommentData;
import com.example.robot_kimsatgat_android.Server.ParamClasses.RecvLikeData;
import com.example.robot_kimsatgat_android.Server.ParamClasses.RecvPoemData;
import com.example.robot_kimsatgat_android.Server.ParamClasses.ReqCommentData;
import com.example.robot_kimsatgat_android.Server.PoemServer;
import com.example.robot_kimsatgat_android.UI.MainActivity;
import com.example.robot_kimsatgat_android.UI.Poem_view;
import com.example.robot_kimsatgat_android.UI.Questionnaire1;
import com.example.robot_kimsatgat_android.UI.WriteActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

public class View_Main extends Fragment {

    private static final String TAG = "mainFragment";
    Toolbar toolbar;

    RecvPoemData recommendedPoem;
    int recom_poem_like_num;
    int poem_id;
    List<RecvCommentData> commentList;
    Poem_view main_poem;
    ImageButton Ibtn_poemlike;
    ImageButton Ibtn_commentsend;
    PoemServer poemServer;

    public View_Main() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_main, container, false);
    }
    @Override
    public void onViewCreated(View view,Bundle savedInstanceState){

        main_poem = view.findViewById(R.id.main_poem);
        Ibtn_poemlike = main_poem.Ibtn_poemlike;
        Ibtn_commentsend = main_poem.Ibtn_commentsend;

        // poem
        poemServer = PoemServer.getPoemServer();
        poemServer.recommendPoem(new Function1<RecvPoemData, Void>() {
            @Override
            public Void invoke(RecvPoemData recvPoemData) {
                try {
                    //recommendedPoem = (RecvPoemData)recvPoemData.clone();
                    Poem item = new Poem(
                            recvPoemData.id,
                            recvPoemData.title,
                            recvPoemData.writer,
                            recvPoemData.content,
                            recvPoemData.likenum,
                            recvPoemData.like
                            );
                    poem_id = item.id;
                    main_poem.setPoem_title(item.getPoem_name());
                    main_poem.setPoem_writer(item.getEditor());
                    main_poem.setPoem_main_view(item.getMain_text());
                    main_poem.setPoem_likenum(item.getLikenum_text());
                    if(Ibtn_poemlike == null){
                        Log.i(TAG,"ibtn null");
                    }
                    if(item.like){
                        Ibtn_poemlike.setImageResource(R.drawable.heart_filled);
                    }else{
                        Ibtn_poemlike.setImageResource(R.drawable.heart);
                    }
                    Ibtn_poemlike.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(!item.like){
                                item.like = true;
                                Ibtn_poemlike.setImageResource(R.drawable.heart_filled_drawable);
                                poemServer.postLikeFeedback();
                                poemServer.postLike(item.id, new Function0<Void>() {
                                    @Override
                                    public Void invoke() {
                                        poemServer.getLike(item.id, new Function1<RecvLikeData, Void>() {
                                            @Override
                                            public Void invoke(RecvLikeData recvLikeData) {
                                                item.likenum = recvLikeData.likenum;
                                                main_poem.setPoem_likenum(Integer.toString(item.likenum));
                                                return null;
                                            }
                                        });
                                        return null;
                                    }
                                });
                            }else{
                                item.like = false;
                                Ibtn_poemlike.setImageResource(R.drawable.heart_drawable);
                                poemServer.deleteLikeFeedback();
                                poemServer.deleteLike(item.id, new Function0<Void>() {
                                    @Override
                                    public Void invoke() {
                                        poemServer.getLike(item.id, new Function1<RecvLikeData, Void>() {
                                            @Override
                                            public Void invoke(RecvLikeData recvLikeData) {
                                                item.likenum = recvLikeData.likenum;
                                                main_poem.setPoem_likenum(Integer.toString(item.likenum));
                                                return null;
                                            }
                                        });
                                        return null;
                                    }
                                });
                            }
                        }
                    });
                }catch(Exception e){
                    Log.e(TAG,e.getMessage());
                }
                return null;


            }
        });

        // comment
        Ibtn_commentsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                poemServer.postComment(poem_id, main_poem.comment_edit.getText().toString(), new Function0<Void>() {
                    @Override
                    public Void invoke() {
                        Toast.makeText(getActivity(),main_poem.comment_edit.getText().toString(), Toast.LENGTH_SHORT).show();
                        return null;
                    }
                });
                main_poem.comment_edit.setText("");
            }
        });

//        main_poem.commentlist = poemServer.getComments(poem_id);

        FloatingActionButton fab = view.findViewById(R.id.poem_write);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), WriteActivity.class);
                startActivity(intent);
            }
        });
    }
}
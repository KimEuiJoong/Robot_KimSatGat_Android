package com.example.robot_kimsatgat_android.UI.Views;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.robot_kimsatgat_android.R;
import com.example.robot_kimsatgat_android.Server.ParamClasses.RecvCommentData;
import com.example.robot_kimsatgat_android.Server.ParamClasses.RecvPoemData;
import com.example.robot_kimsatgat_android.Server.PoemServer;
import com.example.robot_kimsatgat_android.UI.Poem_view;

import java.util.List;

import kotlin.jvm.functions.Function1;

public class View_Main extends Fragment {

    private static final String TAG = "mainFragment";
    Toolbar toolbar;

    RecvPoemData recommendedPoem;
    int recom_poem_like_num;
    List<RecvCommentData> commentList;
    Poem_view main_poem;

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


        //TextView commentWriterTv = findViewById(R.id.textview_comment_writer);
        //TextView commentContentTv = findViewById(R.id.textview_comment_content);
        main_poem = view.findViewById(R.id.main_poem);

        PoemServer poemServer = PoemServer.getPoemServer();
        poemServer.recommendPoem(new Function1<RecvPoemData, Void>() {
            @Override
            public Void invoke(RecvPoemData recvPoemData) {
                try {
                    recommendedPoem = (RecvPoemData)recvPoemData.clone();
                    main_poem.setPoem_title(recommendedPoem.title);
                    main_poem.setPoem_writer(recommendedPoem.writer);
                    main_poem.setPoem_main_view(recommendedPoem.content);
                    main_poem.setPoem_likenum(Integer.toString(recommendedPoem.likenum));
                    poemServer.getComments(recommendedPoem.id, new Function1<List<RecvCommentData>, Void>() {
                        @Override
                        public Void invoke(List<RecvCommentData> recvCommentData) {
                            commentList = recvCommentData;
                            Log.i(TAG,"getcommentinvoke:"+Integer.toString(commentList.size()));
                            try {
                                RecvCommentData cmt = commentList.get(0);
                                //commentWriterTv.setText(cmt.writer);
                                //commentContentTv.setText(cmt.content);
                            }catch(Exception e){
                                Log.e(TAG,e.getMessage());
                            }
                            return null;
                        }
                    });
                }catch(Exception e){
                    Log.i(TAG,"poem clone failed");
                }
                return null;
            }
        });
        //if(item.like){
        //    Ibtn_poemlike.setImageResource(R.drawable.heart_filled);
        //    Ibtn_poemlike.setScaleType(ImageView.ScaleType.FIT_XY);
        //}else{
        //    Ibtn_poemlike.setImageResource(R.drawable.heart);
        //}
        //ImageButton commentSendBtn = view.findViewById(R.id.comment_send);
        //TextView commentEditTv = view.findViewById(R.id.comment_edit);

        //commentSendBtn.setOnClickListener(v->{
        //    try {
        //        //postComment(댓글을 달 시의 번호(id), 댓글의 내용)
        //        poemServer.postComment(recommendedPoem.id, commentEditTv.getText().toString(), ()-> {
        //            //댓글을 단 후 시에 달린 댓글 목록을 새로 가져온다.
        //            poemServer.getComments(recommendedPoem.id,(recvCommentData)->{
        //                commentList = recvCommentData;
        //                Log.i(TAG,"getcommentinvoke:"+Integer.toString(commentList.size()));
        //                try {
        //                    RecvCommentData cmt = commentList.get(0);
        //                    //commentWriterTv.setText(cmt.writer);
        //                    //commentContentTv.setText(cmt.content);
        //                }catch(Exception e){
        //                    Log.e(TAG,e.getMessage());
        //                }
        //                return null;
        //            });
        //            return null;
        //        });
        //    }catch(Exception e){
        //        Log.e(TAG,e.getMessage());
        //    }
        //});
        //FloatingActionButton fab = view.findViewById(R.id.poem_write);
        //fab.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View view) {
        //        Intent intent = new Intent(getActivity(), WriteActivity.class);
        //        startActivity(intent);
        //    }
        //});
    }
}
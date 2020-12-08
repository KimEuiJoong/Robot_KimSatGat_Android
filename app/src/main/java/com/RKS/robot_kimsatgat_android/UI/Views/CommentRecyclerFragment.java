package com.RKS.robot_kimsatgat_android.UI.Views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.RKS.robot_kimsatgat_android.R;
import com.RKS.robot_kimsatgat_android.SampleData.Comment;
import com.RKS.robot_kimsatgat_android.SampleData.Comment_Adapter;
import com.RKS.robot_kimsatgat_android.SampleData.Poem;
import com.RKS.robot_kimsatgat_android.Server.ParamClasses.RecvCommentData;
import com.RKS.robot_kimsatgat_android.Server.ParamClasses.RecvLikeData;
import com.RKS.robot_kimsatgat_android.Server.PoemServer;
import com.RKS.robot_kimsatgat_android.UI.Poem_view;
import com.RKS.robot_kimsatgat_android.ViewModels.ViewModelMain;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

public class CommentRecyclerFragment extends Fragment {

    private static final String TAG = "CommentRecyclerFragment";
    private ViewModelMain viewModelMain;
    RecyclerView commentRecyclerView;
    Comment_Adapter commentRecyclerAdapter;
    GridLayoutManager layoutManager_comment;
    LayoutAnimationController animation_comment;
    int animResId_comment;
    Intent intent;
    int poem_id;
    Poem_view main_poem;
    ImageButton Ibtn_poemlike;
    ImageButton Ibtn_commentsend;
    PoemServer poemServer;
    TextView TV_commentEdit;

    public CommentRecyclerFragment(){
        animResId_comment = R.anim.layout_animation;
    }

    public CommentRecyclerFragment(int FragmentResId, int RecyclerResId,int AnimResId, String ListName){
        animResId_comment = AnimResId;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        poem_id = getArguments().getInt("poem_id");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        /*
        view_main을 사용하지 않고, fragment_view_detail을 새로 만들었음.
         */
        return inflater.inflate(R.layout.fragment_view_detail, container, false);
    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);

        commentRecyclerAdapter = new Comment_Adapter();
        commentRecyclerAdapter.setHasStableIds(true);
        animation_comment = AnimationUtils.loadLayoutAnimation(getActivity(),animResId_comment);
        layoutManager_comment = new GridLayoutManager(getActivity(),1);
        commentRecyclerView = view.findViewById(R.id.comment_recyclerView);
        commentRecyclerView.setLayoutManager(layoutManager_comment);
        commentRecyclerView.setAdapter(commentRecyclerAdapter);


        viewModelMain = new ViewModelProvider(this).get(ViewModelMain.class);
        main_poem = view.findViewById(R.id.main_poem);
        Ibtn_poemlike = main_poem.Ibtn_poemlike;
        /*
        poem_id로 시의 정보를 받아 main_poem을 채운다.
        ++ 좋아요 버튼 작업.
        View_Main과 많이 중복되기에 따로 작업하는건 별로 좋은 선택이 아니지만, 클래스 하나 또 만들기 귀찮아서 이렇게 작업했음.
         */
        viewModelMain.getPoem(poem_id).observe(getViewLifecycleOwner(),recvPoemData -> {
            Poem item = new Poem(
                    recvPoemData.id,
                    recvPoemData.title,
                    recvPoemData.writer,
                    recvPoemData.content,
                    recvPoemData.likenum,
                    recvPoemData.like
            );
            main_poem.setPoem_title(item.getPoem_name());
            main_poem.setPoem_writer(item.getEditor());
            main_poem.setPoem_main_view(item.getMain_text());
            main_poem.setPoem_likenum(item.getLikenum_text());
            poemServer = PoemServer.getPoemServer();
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
        });
        /*
        getComments. poem_id로 댓글 리스트를 받아온다.
         */
        getComments();
        TV_commentEdit = view.findViewById(R.id.comment_edit);
        Ibtn_commentsend = view.findViewById(R.id.comment_send);
        Ibtn_commentsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                poemServer.postComment(poem_id, TV_commentEdit.getText().toString(), new Function0<Void>() {
                    @Override
                    public Void invoke() {
                        /*
                        댓글을 달고나서 댓글 리스트 새로고침.
                         */
                        getComments();
                        //Toast.makeText(getActivity(),TV_commentEdit.getText().toString(), Toast.LENGTH_SHORT).show();
                        return null;
                    }
                });
                TV_commentEdit.setText("");
            }
        });
    }
    private void getComments(){
        viewModelMain.getComments(poem_id).observe(getViewLifecycleOwner(), CommentList -> {
            commentRecyclerView.setLayoutAnimation(animation_comment);
                for(RecvCommentData commentData : CommentList){
                    commentRecyclerAdapter.addComment(new Comment(commentData.id,commentData.writer,commentData.content));
                    Log.i(TAG,Integer.toString(commentData.id) + " "+ commentData.writer + " " + commentData.content);
                }
                commentRecyclerAdapter.notifyDataSetChanged();
            }
        );
    }
}

package com.example.robot_kimsatgat_android.UI.Views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.example.robot_kimsatgat_android.R;
import com.example.robot_kimsatgat_android.SampleData.Comment;
import com.example.robot_kimsatgat_android.SampleData.Comment_Adapter;
import com.example.robot_kimsatgat_android.SampleData.Poem;
import com.example.robot_kimsatgat_android.SampleData.PoemRecyclerAdapter;
import com.example.robot_kimsatgat_android.Server.ParamClasses.RecvCommentData;
import com.example.robot_kimsatgat_android.Server.ParamClasses.RecvPoemBriefData;
import com.example.robot_kimsatgat_android.ViewModels.ViewModelMain;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public abstract class CommentRecyclerFragment extends Fragment {

    private ViewModelMain viewModelMain_comment;
    RecyclerView commentRecyclerView;
    Comment_Adapter commentRecyclerAdapter;
    GridLayoutManager layoutManager_comment;
    LayoutAnimationController animation_comment;
    int fragmentResId_comment;
    int recyclerResId_comment;
    int animResId_comment;
    String listName_comment;

    public CommentRecyclerFragment(int FragmentResId, int RecyclerResId, String ListName){

        fragmentResId_comment = FragmentResId;
        recyclerResId_comment = RecyclerResId;
        animResId_comment = R.anim.layout_animation;
        listName_comment = ListName;
    }

    public CommentRecyclerFragment(int FragmentResId, int RecyclerResId,int AnimResId, String ListName){

        fragmentResId_comment = FragmentResId;
        recyclerResId_comment = RecyclerResId;
        animResId_comment = AnimResId;
        listName_comment = ListName;

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(fragmentResId_comment, container, false);
    }
    @Override
    public void onViewCreated(View view,Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);

        commentRecyclerAdapter = new Comment_Adapter();
        commentRecyclerAdapter.setHasStableIds(true);
        animation_comment = AnimationUtils.loadLayoutAnimation(getActivity(),animResId_comment);
        layoutManager_comment = new GridLayoutManager(getActivity(),1);
        commentRecyclerView = view.findViewById(recyclerResId_comment);
        commentRecyclerView.setLayoutManager(layoutManager_comment);
        commentRecyclerView.setAdapter(commentRecyclerAdapter);

        viewModelMain_comment = new ViewModelProvider(this).get(ViewModelMain.class);
//        viewModelMain_comment.getList(listName_comment).observe(getViewLifecycleOwner(), CommentList -> {
//            commentRecyclerView.setLayoutAnimation(animation_comment);
//                    for(RecvCommentData commentData : CommentList){
//                        commentRecyclerAdapter.addComment(new Comment(commentData.comment_id,commentData.content));
//                    }
//                    commentRecyclerAdapter.notifyDataSetChanged();
//                }
//        );
    }

}

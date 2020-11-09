package com.example.robot_kimsatgat_android.UI.Views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.robot_kimsatgat_android.R;
import com.example.robot_kimsatgat_android.SampleData.Poem;
import com.example.robot_kimsatgat_android.SampleData.Sample_poem_Adapter;
import com.example.robot_kimsatgat_android.Server.ParamClasses.RecvPoemBriefData;
import com.example.robot_kimsatgat_android.ViewModels.ViewModel_Main;

import java.util.List;

public class View_MyPoem extends Fragment {
    //PoemServer poemServer;
    private ViewModel_Main viewModelMain;
    RecyclerView mypoem_recyclerView;
    Sample_poem_Adapter mypoem_adapter;
    List<RecvPoemBriefData> poemBriefDataList;
    GridLayoutManager layoutManager;
    LayoutAnimationController animation;
    View view;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_mypoem, container, false);
    }
    @Override
    public void onViewCreated(View view,Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        mypoem_adapter = new Sample_poem_Adapter();
        mypoem_adapter.setHasStableIds(true);
        //poemServer = PoemServer.getPoemServer();
        viewModelMain = new ViewModelProvider(this).get(ViewModel_Main.class);
        animation = AnimationUtils.loadLayoutAnimation(getActivity(),R.anim.layout_animation);
        layoutManager = new GridLayoutManager(getActivity(),1);
        mypoem_recyclerView = view.findViewById(R.id.mypoem_recyclerView);
        mypoem_recyclerView.setLayoutManager(layoutManager);
        mypoem_recyclerView.setAdapter(mypoem_adapter);
        viewModelMain.getMyPoemList().observe(getViewLifecycleOwner(), myPoemList -> {
            mypoem_recyclerView.setLayoutAnimation(animation);
               for(RecvPoemBriefData poemBriefData : myPoemList){
                   mypoem_adapter.addPoem(new Poem(poemBriefData.id,poemBriefData.title,poemBriefData.writer,"내용",poemBriefData.likenum,poemBriefData.like));
               }
               mypoem_adapter.notifyDataSetChanged();
            }
        );
    }
    @Override
    public void onStart(){
        super.onStart();
        //mypoem_recyclerView = view.findViewById(R.id.mypoem_recyclerView);
        //mypoem_recyclerView.setLayoutManager(layoutManager);
        //mypoem_recyclerView.setAdapter(mypoem_adapter);

        //poemServer.getMyPoemList(new Function1<List<RecvPoemBriefData>, Void>() {
        //    @Override
        //    public Void invoke(List<RecvPoemBriefData> recvPoemBriefData) {
        //        mypoem_recyclerView.setLayoutAnimation(animation);
        //        poemBriefDataList = new ArrayList<RecvPoemBriefData>(recvPoemBriefData);
        //        for(RecvPoemBriefData poemBriefData : poemBriefDataList){
        //            mypoem_adapter.addPoem(new Poem(poemBriefData.title,poemBriefData.writer,"내용"));
        //        }
        //        mypoem_adapter.notifyDataSetChanged();
        //        return null;
        //    }
        //});
    }

}
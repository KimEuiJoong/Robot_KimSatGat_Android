package com.RKS.robot_kimsatgat_android.UI.Views;

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

import com.RKS.robot_kimsatgat_android.R;
import com.RKS.robot_kimsatgat_android.SampleData.Poem;
import com.RKS.robot_kimsatgat_android.SampleData.PoemRecyclerAdapter;
import com.RKS.robot_kimsatgat_android.Server.ParamClasses.RecvPoemBriefData;
import com.RKS.robot_kimsatgat_android.ViewModels.ViewModelMain;

public abstract class PoemRecyclerFragment extends Fragment {
    /**
        시의 제목, 작가, 좋아요 뷰 리스트를 보여주는 Fragment의 베이스 클래스.
        먼저 PoemRecyclerFragment를 상속받고, 그 다음 생성자에 super를 호출하자.
     */
    private ViewModelMain viewModelMain;
    RecyclerView poemRecyclerView;
    PoemRecyclerAdapter poemRecyclerAdapter;
    GridLayoutManager layoutManager;
    LayoutAnimationController animation;
    int fragmentResId;
    int recyclerResId;
    int animResId;
    String listName;
    public PoemRecyclerFragment(int FragmentResId, int RecyclerResId, String ListName){
        /**
         *
         * @param FragmentResId 사용하고 싶은 Fragment의 레이아웃 xml id. findViewById에 쓰는 그거.
         *
         * @param RecyclerResId Fragment 레이아웃 안에 있는 리사이클러 뷰의 id.
         *
         * @param ListName 가져오고 싶은 List의 이름. MyPoemList, LikeList, MyRecommendList 세가지를 지원.
         *
          */
        fragmentResId = FragmentResId;
        recyclerResId = RecyclerResId;
        animResId = R.anim.layout_animation;
        listName = ListName;
    }
    public PoemRecyclerFragment(int FragmentResId, int RecyclerResId,int AnimResId, String ListName){
        /**
         * 리스트가 올라가거나 내려가는 애니메이션을 따로 설정하기 위한 생성자.
         *
         * @param FragmentResId 사용하고 싶은 Fragment의 레이아웃 xml id. findViewById에 쓰는 그거.
         *
         * @param RecyclerResId Fragment 레이아웃 안에 있는 리사이클러 뷰의 id.
         *
         * @param AnimResId 애니메이션 xml id.
         *
         * @param ListName 가져오고 싶은 List의 이름. MyPoemList, LikeList, MyRecommendList 세가지를 지원.
         *
         */
        fragmentResId = FragmentResId;
        recyclerResId = RecyclerResId;
        animResId = AnimResId;
        listName = ListName;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(fragmentResId, container, false);
    }
    @Override
    public void onViewCreated(View view,Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);

        poemRecyclerAdapter = new PoemRecyclerAdapter();
        poemRecyclerAdapter.setHasStableIds(true);
        animation = AnimationUtils.loadLayoutAnimation(getActivity(),animResId);
        layoutManager = new GridLayoutManager(getActivity(),1);
        poemRecyclerView = view.findViewById(recyclerResId);
        poemRecyclerView.setLayoutManager(layoutManager);
        poemRecyclerView.setAdapter(poemRecyclerAdapter);

        viewModelMain = new ViewModelProvider(this).get(ViewModelMain.class);
        viewModelMain.getList(listName).observe(getViewLifecycleOwner(), PoemList -> {
            poemRecyclerView.setLayoutAnimation(animation);
               for(RecvPoemBriefData poemBriefData : PoemList){
                   poemRecyclerAdapter.addPoem(new Poem(poemBriefData.id,poemBriefData.title,poemBriefData.writer,"",poemBriefData.likenum,poemBriefData.like));
               }
               poemRecyclerAdapter.notifyDataSetChanged();
            }
        );
    }
}
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
import com.example.robot_kimsatgat_android.Server.PoemServer;
import com.example.robot_kimsatgat_android.ViewModels.ViewModel_Main;

public class View_LikeList extends Fragment {
    private ViewModel_Main viewModelMain;
    RecyclerView likelist_recyclerView;
    Sample_poem_Adapter likelist_adapter;
    GridLayoutManager layoutManager;
    LayoutAnimationController animation;

    public View_LikeList(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_likelist, container, false);
    }
    @Override
    public void onViewCreated(View view,Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        likelist_adapter = new Sample_poem_Adapter();
        likelist_adapter.setHasStableIds(true);
        viewModelMain = new ViewModelProvider(this).get(ViewModel_Main.class);
        animation = AnimationUtils.loadLayoutAnimation(getActivity(),R.anim.layout_animation);
        layoutManager = new GridLayoutManager(getActivity(),1);
        likelist_recyclerView = view.findViewById(R.id.likelist_recyclerView);
        likelist_recyclerView.setLayoutManager(layoutManager);
        likelist_recyclerView.setAdapter(likelist_adapter);

        viewModelMain.getLikeList().observe(getViewLifecycleOwner(),myLikeList->{
            likelist_recyclerView.setLayoutAnimation(animation);
            PoemServer poemServer = PoemServer.getPoemServer();
            for(RecvPoemBriefData poemBriefData : myLikeList){
                int id = poemBriefData.id;
                likelist_adapter.addPoem(new Poem(poemBriefData.id,poemBriefData.title,poemBriefData.writer,"내용",poemBriefData.likenum,poemBriefData.like));
            }
            likelist_adapter.notifyDataSetChanged();
            //RecyclerView.ViewHolder viewHolder = likelist_recyclerView.findViewHolderForItemId(poemBriefData.id);
            //if(viewHolder == null){
            //    Log.i("pbd","vh is null");
            //}
            //if(viewHolder.itemView == null){
            //    Log.i("pbd","itemview is null");
            //}
            //ImageButton Ibtn_poemlike = viewHolder.itemView.findViewById(R.id.likeIButton);
            //Ibtn_poemlike.setOnClickListener(new View.OnClickListener() {
            //    @Override
            //    public void onClick(View view) {
            //        Poem item = likelist_adapter.items.get(id);
            //        if(!likelist_adapter.items.get(id).getLike()){
            //            item.like = true;
            //            Ibtn_poemlike.setImageResource(R.drawable.heart_filled);
            //            Ibtn_poemlike.setScaleType(ImageView.ScaleType.FIT_XY);
            //            poemServer.postLike(item.id, new Function0<Void>() {
            //                @Override
            //                public Void invoke() {
            //                    likelist_adapter.notifyItemChanged(item.id);
            //                    return null;
            //                }
            //            });
            //        }else{
            //            item.like = false;
            //            Ibtn_poemlike.setImageResource(R.drawable.heart);
            //            poemServer.deleteLike(item.id, new Function0<Void>() {
            //                @Override
            //                public Void invoke() {
            //                    likelist_adapter.notifyItemChanged(item.id);
            //                    return null;
            //                }
            //            });
            //        }
            //        Log.i("poemAdapter","clicked");
            //    }
            //});
        });






    }
}

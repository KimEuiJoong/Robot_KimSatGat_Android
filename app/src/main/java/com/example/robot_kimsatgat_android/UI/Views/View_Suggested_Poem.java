package com.example.robot_kimsatgat_android.UI.Views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.robot_kimsatgat_android.R;
import com.example.robot_kimsatgat_android.SampleData.Sample_poem_Adapter;

public class View_Suggested_Poem extends Fragment {
    RecyclerView suggested_recyclerView;
    Sample_poem_Adapter suggested_adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_suggestedpoem, container, false);
    }
    @Override
    public void onViewCreated(View view,Bundle savedInstanceState){

        suggested_recyclerView = view.findViewById(R.id.suggested_recyclerView);
        LayoutAnimationController animation =
                AnimationUtils.loadLayoutAnimation(getActivity(),R.anim.layout_animation);
        suggested_recyclerView.setLayoutAnimation(animation);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),1);
        suggested_recyclerView.setLayoutManager(layoutManager);

        suggested_adapter = new Sample_poem_Adapter();


        suggested_recyclerView.setAdapter(suggested_adapter);
    }
}

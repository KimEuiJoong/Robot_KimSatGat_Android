package com.example.robot_kimsatgat_android.UI.Views;

import android.os.Bundle;

import com.example.robot_kimsatgat_android.R;

import androidx.fragment.app.FragmentTransaction;

public class View_MyPoem extends PoemRecyclerFragment {

    public View_MyPoem(){
        super(R.layout.fragment_view_mypoem,R.id.mypoem_recyclerView,"MyPoemList");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

        transaction.addToBackStack(null);
    }

}
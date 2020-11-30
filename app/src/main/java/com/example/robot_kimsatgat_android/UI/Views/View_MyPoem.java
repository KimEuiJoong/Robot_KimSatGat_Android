package com.example.robot_kimsatgat_android.UI.Views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.robot_kimsatgat_android.R;

import androidx.fragment.app.FragmentTransaction;

public class View_MyPoem extends PoemRecyclerFragment {

    public View_MyPoem(){
        super(R.layout.fragment_view_mypoem,R.id.mypoem_recyclerView,"MyPoemList");
    }

}
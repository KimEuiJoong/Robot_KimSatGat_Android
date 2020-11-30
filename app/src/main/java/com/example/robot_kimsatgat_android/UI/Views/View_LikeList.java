package com.example.robot_kimsatgat_android.UI.Views;

import android.os.Bundle;

import com.example.robot_kimsatgat_android.R;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class View_LikeList extends PoemRecyclerFragment {

    public View_LikeList(){
        super(R.layout.fragment_view_likelist, R.id.likelist_recyclerView,"LikeList");
    }

}

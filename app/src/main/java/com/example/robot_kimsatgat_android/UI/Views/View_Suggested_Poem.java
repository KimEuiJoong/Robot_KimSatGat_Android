package com.example.robot_kimsatgat_android.UI.Views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.robot_kimsatgat_android.R;
import com.example.robot_kimsatgat_android.UI.MainActivity;
import com.example.robot_kimsatgat_android.UI.Poem_view;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class View_Suggested_Poem extends PoemRecyclerFragment {

    public View_Suggested_Poem() {
        super(R.layout.fragment_view_suggestedpoem,R.id.suggested_recyclerView,"MyRecommendList");
    }

}

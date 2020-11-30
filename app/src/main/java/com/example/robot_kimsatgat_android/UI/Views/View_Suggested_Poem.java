package com.example.robot_kimsatgat_android.UI.Views;

import android.os.Bundle;
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        FragmentTransaction transaction = getActivity()
//                .getSupportFragmentManager()
//                .beginTransaction()
//                .add(R.id.nav_suggestionlist,View_Main);
//
//        // 해당 transaction 을 Back Stack 에 저장
//        transaction.addToBackStack(null);
//        // transaction 실행
//        transaction.commit();

    }

}

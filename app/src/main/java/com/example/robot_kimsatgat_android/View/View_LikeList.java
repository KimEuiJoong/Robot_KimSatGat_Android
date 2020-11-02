package com.example.robot_kimsatgat_android.View;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.robot_kimsatgat_android.R;
import com.example.robot_kimsatgat_android.SampleData.Sample_poem_Adapter;

public class View_LikeList extends AppCompatActivity {
    RecyclerView likelist_recyclerView;
    Sample_poem_Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_view_likelist);

        likelist_recyclerView = findViewById(R.id.likelist_recyclerView);

        GridLayoutManager layoutManager = new GridLayoutManager(this,1);
        likelist_recyclerView.setLayoutManager(layoutManager);

        adapter = new Sample_poem_Adapter();

        likelist_recyclerView.setAdapter(adapter);
    }
}

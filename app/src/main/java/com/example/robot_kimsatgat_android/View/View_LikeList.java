package com.example.robot_kimsatgat_android.View;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.robot_kimsatgat_android.R;
import com.example.robot_kimsatgat_android.SampleData.Poem;
import com.example.robot_kimsatgat_android.SampleData.Sample_poem_Adapter;

public class View_LikeList extends AppCompatActivity {
    RecyclerView likelist_recyclerView;
    Sample_poem_Adapter likelist_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_view_likelist);

        likelist_recyclerView = findViewById(R.id.likelist_recyclerView);

        GridLayoutManager layoutManager = new GridLayoutManager(this,1);
        likelist_recyclerView.setLayoutManager(layoutManager);

        likelist_adapter = new Sample_poem_Adapter();

        likelist_adapter.addPoem(new Poem("시1","류준현","이런\n저런\n시"));
        likelist_adapter.addPoem(new Poem("시2","류준현","이런\n저런\n시"));
        likelist_adapter.addPoem(new Poem("시3","류준현","이런\n저런\n시"));
        likelist_adapter.addPoem(new Poem("시4","류준현","이런\n저런\n시"));
        likelist_adapter.addPoem(new Poem("시5","류준현","이런\n저런\n시"));
        likelist_adapter.addPoem(new Poem("시6","류준현","이런\n저런\n시"));
        likelist_adapter.addPoem(new Poem("시7","류준현","이런\n저런\n시"));
        likelist_adapter.addPoem(new Poem("시8","류준현","이런\n저런\n시"));
        likelist_adapter.addPoem(new Poem("시9","류준현","이런\n저런\n시"));
        likelist_adapter.addPoem(new Poem("시10","류준현","이런\n저런\n시"));

        likelist_recyclerView.setAdapter(likelist_adapter);
    }
}

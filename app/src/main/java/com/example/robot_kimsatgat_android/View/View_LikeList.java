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

        likelist_adapter.addPoem(new Poem("장인","강찬밥","더운밥 찬밥\n가리려고 하지말고\n골고루 먹어보자",true));
        likelist_adapter.addPoem(new Poem("상승","야망","산 넘고 바다 넘어\n절벽을 올라가자\n정상의 패자를 꺾기위해",true));
        likelist_adapter.addPoem(new Poem("미친 인생","홍민기","땡기고\n밀어내고\n이겨보자",true));
        likelist_adapter.addPoem(new Poem("폭룡의 시","김성모",
                "붉은 피, 보이지\n않는 폭룡의 전투\n눈물겨운 기억들\n망가진 내 육체,\n내 가슴에 묻고\n승리여\n나에게 오라\n자만도 오만도\n그것은 폭룡의 검\n어느날 전투의\n패배에 쓰러졌어도\n숱한 전투의\n종착지라 생각지마라\n육체는 단명이고\n근성은 영원한것\n대류...\n폭룡이 최고다.",true));
        likelist_adapter.addPoem(new Poem("일상","이효근","오늘도\n내일도\n사는게 일상",true));
        likelist_adapter.addPoem(new Poem("오히려 좋아","구름신도","아무리 슬퍼도\n아무리 나빠도\n오히려 좋아",true));
        likelist_adapter.addPoem(new Poem("시7","류준현","이런\n저런\n시",true));
        likelist_adapter.addPoem(new Poem("시8","류준현","이런\n저런\n시",true));
        likelist_adapter.addPoem(new Poem("시9","류준현","이런\n저런\n시",true));
        likelist_adapter.addPoem(new Poem("시10","류준현","이런\n저런\n시",true));

        likelist_recyclerView.setAdapter(likelist_adapter);
    }
}

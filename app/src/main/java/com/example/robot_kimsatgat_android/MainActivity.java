package com.example.robot_kimsatgat_android;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.robot_kimsatgat_android.Questionnaire.Questionnaire1;
import com.example.robot_kimsatgat_android.Questionnaire.Questionnaire2;
import com.example.robot_kimsatgat_android.SampleData.Sample_poem_Adapter;
import com.example.robot_kimsatgat_android.Server.ParamClasses.RecvCommentData;
import com.example.robot_kimsatgat_android.Server.ParamClasses.RecvPoemData;
import com.example.robot_kimsatgat_android.Server.PoemServer;
import com.example.robot_kimsatgat_android.UI.Poem_Write.Poem_Write;
import com.example.robot_kimsatgat_android.View.Poem_view;
import com.example.robot_kimsatgat_android.View.View_LikeList;
import com.example.robot_kimsatgat_android.View.View_MyPoem;
import com.example.robot_kimsatgat_android.View.View_Suggested_Poem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import kotlin.jvm.functions.Function1;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    Questionnaire1 Q1 = (Questionnaire1)Questionnaire1._Questionnaire1;
    Questionnaire2 Q2 = (Questionnaire2)Questionnaire2._Questionnaire2;

    private DrawerLayout mDrawerLayout;
    private Context context = this;

    RecvPoemData recommendedPoem;
    int recom_poem_like_num;
    List<RecvCommentData> commentList;
    private AppBarConfiguration mAppBarConfiguration;

    Toolbar toolbar;

    Poem_view main_poem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        GlobalApplication globalApplication = (GlobalApplication)getApplication();
        String user_name = globalApplication.getName();
        Q1.finish();
        Q2.finish();

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false); // 기존 title 지우기
        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼 만들기
        actionBar.setHomeAsUpIndicator(R.drawable.hamburger); //뒤로가기 버튼 이미지 지정

        //TextView commentWriterTv = findViewById(R.id.textview_comment_writer);
        //TextView commentContentTv = findViewById(R.id.textview_comment_content);
        main_poem = findViewById(R.id.main_poem);

        PoemServer poemServer = PoemServer.getPoemServer();
        poemServer.recommendPoem(new Function1<RecvPoemData, Void>() {
            @Override
            public Void invoke(RecvPoemData recvPoemData) {
                try {
                    recommendedPoem = (RecvPoemData)recvPoemData.clone();
                    main_poem.setPoem_title(recommendedPoem.title);
                    main_poem.setPoem_writer(recommendedPoem.writer);
                    main_poem.setPoem_main_view(recommendedPoem.content);
                    main_poem.setPoem_likenum(Integer.toString(recommendedPoem.likenum));
                    poemServer.getComments(recommendedPoem.id, new Function1<List<RecvCommentData>, Void>() {
                        @Override
                        public Void invoke(List<RecvCommentData> recvCommentData) {
                            commentList = recvCommentData;
                            Log.i(TAG,"getcommentinvoke:"+Integer.toString(commentList.size()));
                            try {
                                RecvCommentData cmt = commentList.get(0);
                                //commentWriterTv.setText(cmt.writer);
                                //commentContentTv.setText(cmt.content);
                            }catch(Exception e){
                                Log.e(TAG,e.getMessage());
                            }
                            return null;
                        }
                    });
                }catch(Exception e){
                    Log.i(TAG,"poem clone failed");
                }
                return null;
            }
        });

        ImageButton commentSendBtn = findViewById(R.id.comment_send);
        TextView commentEditTv = findViewById(R.id.comment_edit);

        commentSendBtn.setOnClickListener(v->{
            try {
                //postComment(댓글을 달 시의 번호(id), 댓글의 내용)
                poemServer.postComment(recommendedPoem.id, commentEditTv.getText().toString(), ()-> {
                    //댓글을 단 후 시에 달린 댓글 목록을 새로 가져온다.
                    poemServer.getComments(recommendedPoem.id,(recvCommentData)->{
                        commentList = recvCommentData;
                        Log.i(TAG,"getcommentinvoke:"+Integer.toString(commentList.size()));
                        try {
                            RecvCommentData cmt = commentList.get(0);
                            //commentWriterTv.setText(cmt.writer);
                            //commentContentTv.setText(cmt.content);
                        }catch(Exception e){
                            Log.e(TAG,e.getMessage());
                        }
                        return null;
                    });
                    return null;
                });
            }catch(Exception e){
                Log.e(TAG,e.getMessage());
            }
        });

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,mDrawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        Sample_poem_Adapter adapter = new Sample_poem_Adapter();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView drawerNameText = (TextView)headerView.findViewById(R.id.drawer_name_textView);
        drawerNameText.setText(user_name);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        //NavController navController = Navigation.findNavController(this, R.id.nav_view);
        //NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        //NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();

                int id = menuItem.getItemId();
                String title = menuItem.getTitle().toString();

                if(id == R.id.nav_suggestionlist){
                    // 화면 전환 코드
                    Intent intent = new Intent(MainActivity.this, View_Suggested_Poem.class);
                    startActivity(intent);
                    Toast.makeText(context, title + "클릭 : nav_suggestionlist", Toast.LENGTH_SHORT).show();
                }
                else if(id == R.id.nav_likelist){
                    // 화면 전환 코드
                    Intent intent = new Intent (MainActivity.this, View_LikeList.class);
                    startActivity(intent);
                    Toast.makeText(context, title + "클릭 : nav_likelist", Toast.LENGTH_SHORT).show();
                }
                else if(id == R.id.nav_writtedlist){
                    // 화면 전환 코드
                    Intent intent = new Intent (MainActivity.this, View_MyPoem.class);
                    startActivity(intent);
                    Toast.makeText(context, title + "클릭 :nav_writtedlist", Toast.LENGTH_SHORT).show();
                }

                return true;
            }
        });

        FloatingActionButton fab = findViewById(R.id.poem_write);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Poem_Write.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ // 왼쪽 상단 버튼 눌렀을 때
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

//    private AppBarConfiguration mAppBarConfiguration;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.test_drawer);
//
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = findViewById(R.id.poem_write);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Poem_write을 클릭했습니다", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//
//        // drawerlayout
//        DrawerLayout drawer = findViewById(R.id.drawer_layout);
//        NavigationView navigationView = findViewById(R.id.nav_view);
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        mAppBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
//                .setDrawerLayout(drawer)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
//        NavigationUI.setupWithNavController(navigationView, navController);
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main_settings, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onSupportNavigateUp() {
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
//                || super.onSupportNavigateUp();
//    }

}

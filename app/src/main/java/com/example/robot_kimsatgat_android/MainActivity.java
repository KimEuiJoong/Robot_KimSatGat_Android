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

import com.example.robot_kimsatgat_android.Questionnaire.Questionnaire1;
import com.example.robot_kimsatgat_android.Questionnaire.Questionnaire2;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.robot_kimsatgat_android.Server.ParamClasses.RecvCommentData;
import com.example.robot_kimsatgat_android.Server.ParamClasses.RecvPoemData;
import com.example.robot_kimsatgat_android.Server.PoemServer;
import com.example.robot_kimsatgat_android.UI.Poem_Write.Poem_Write;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

import com.example.robot_kimsatgat_android.Questionnaire.Questionnaire1;
import com.example.robot_kimsatgat_android.Questionnaire.Questionnaire2;
import com.example.robot_kimsatgat_android.View.Poem_view;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

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

    Poem_view main_poem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        GlobalApplication globalApplication = (GlobalApplication)getApplication();
        String user_name = globalApplication.getName();
        Q1.finish();
        Q2.finish();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false); // 기존 title 지우기
        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼 만들기
        actionBar.setHomeAsUpIndicator(R.drawable.hamburger); //뒤로가기 버튼 이미지 지정

        TextView poemTitleTv = findViewById(R.id.textView_poem_title);
        TextView poemWriterTv = findViewById(R.id.textView_poet);
        TextView poemContentTv = findViewById(R.id.textView_poem_content);
        TextView commentWriterTv = findViewById(R.id.textview_comment_writer);
        TextView commentContentTv = findViewById(R.id.textview_comment_content);

        PoemServer poemServer = PoemServer.getPoemServer();
        poemServer.recommendPoem(new Function1<RecvPoemData, Void>() {
            @Override
            public Void invoke(RecvPoemData recvPoemData) {
                try {
                    recommendedPoem = (RecvPoemData)recvPoemData.clone();
                    poemTitleTv.setText(recommendedPoem.title);
                    poemWriterTv.setText(recommendedPoem.writer);
                    poemContentTv.setText(recommendedPoem.content);
                    poemServer.getComments(recommendedPoem.id, new Function1<List<RecvCommentData>, Void>() {
                        @Override
                        public Void invoke(List<RecvCommentData> recvCommentData) {
                            commentList = recvCommentData;
                            Log.i(TAG,"getcommentinvoke:"+Integer.toString(commentList.size()));
                            try {
                                RecvCommentData cmt = commentList.get(0);
                                commentWriterTv.setText(cmt.writer);
                                commentContentTv.setText(cmt.content);
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

        commentSendBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                try {
                    //postComment(댓글을 달 시의 번호(id), 댓글의 내용)
                    poemServer.postComment(recommendedPoem.id, commentEditTv.getText().toString(), new Function0<Void>() {
                        @Override
                        public Void invoke() {
                            //시를 달고나서, 새로고침.
                            poemServer.getComments(recommendedPoem.id, new Function1<List<RecvCommentData>, Void>() {
                                @Override
                                public Void invoke(List<RecvCommentData> recvCommentData) {
                                    commentList = recvCommentData;
                                    Log.i(TAG,"getcommentinvoke:"+Integer.toString(commentList.size()));
                                    try {
                                        RecvCommentData cmt = commentList.get(0);
                                        commentWriterTv.setText(cmt.writer);
                                        commentContentTv.setText(cmt.content);
                                    }catch(Exception e){
                                        Log.e(TAG,e.getMessage());
                                    }

                                    return null;
                                }
                            });
                            return null;
                        }
                    });
                }catch(Exception e){
                    //시가 아직 추천되지 않았을 경우, recommendedPoem가 null이므로 NullPointerException이 뜰것.
                    Log.e(TAG,e.getMessage());
                }
            }
        });

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
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
                    Toast.makeText(context, title + "클릭 : nav_suggestionlist", Toast.LENGTH_SHORT).show();
                }
                else if(id == R.id.nav_likelist){
                    Toast.makeText(context, title + "클릭 : nav_likelist", Toast.LENGTH_SHORT).show();
                }
                else if(id == R.id.nav_writtedlist){
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

        main_poem = findViewById(R.id.main_poem);
        main_poem.setPoem_title("서시");
        main_poem.setPoem_writer("윤동주");
        main_poem.setPoem_main_view("죽는 날까지 하늘을 우러러\n한 점 부끄럼이 없기를\n잎새에 이는 바람에도\n나는 괴로워했다\n별을 노래하는 마음으로\n모든 죽어가는 것을 사랑해야지\n그리고 나한테 주어진 길을\n걸어가야겠다\n\n오늘 밤에도 별이 바람에 스치운다");
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

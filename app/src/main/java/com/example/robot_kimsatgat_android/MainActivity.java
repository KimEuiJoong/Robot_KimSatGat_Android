package com.example.robot_kimsatgat_android;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.robot_kimsatgat_android.DB.Poem_Write;
import com.example.robot_kimsatgat_android.Questionnaire.Questionnaire1;
import com.example.robot_kimsatgat_android.Questionnaire.Questionnaire2;
import com.example.robot_kimsatgat_android.SampleData.Sample_poem_Adapter;
import com.example.robot_kimsatgat_android.View.Poem_view;
import com.example.robot_kimsatgat_android.View.View_LikeList;
import com.example.robot_kimsatgat_android.View.View_MyPoem;
import com.example.robot_kimsatgat_android.View.View_Suggested_Poem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity{

    Questionnaire1 Q1 = (Questionnaire1)Questionnaire1._Questionnaire1;
    Questionnaire2 Q2 = (Questionnaire2)Questionnaire2._Questionnaire2;

    private DrawerLayout mDrawerLayout;
    private Context context = this;

    Toolbar toolbar;

    Poem_view main_poem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Q1.finish();
        Q2.finish();

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false); // 기존 title 지우기
        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼 만들기
        actionBar.setHomeAsUpIndicator(R.drawable.hamburger); //뒤로가기 버튼 이미지 지정

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,mDrawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        Sample_poem_Adapter adapter = new Sample_poem_Adapter();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
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

        //메인에 뜰 시 설정

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

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
import androidx.fragment.app.Fragment;

import com.example.robot_kimsatgat_android.DB.Poem_Write;
import com.example.robot_kimsatgat_android.Questionnaire.Questionnaire1;
import com.example.robot_kimsatgat_android.Questionnaire.Questionnaire2;
import com.example.robot_kimsatgat_android.UI.FragmentCallback;
import com.example.robot_kimsatgat_android.UI.Like_List.Like_List_Fragment;
import com.example.robot_kimsatgat_android.UI.Poem_Written_By_Me.Poem_Written_By_Me_Fragment;
import com.example.robot_kimsatgat_android.UI.View_Suggested_Poem.View_Suggested_Poem_Fragment;
import com.example.robot_kimsatgat_android.View.Poem_view;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, FragmentCallback {

    Questionnaire1 Q1 = (Questionnaire1)Questionnaire1._Questionnaire1;
    Questionnaire2 Q2 = (Questionnaire2)Questionnaire2._Questionnaire2;

    private DrawerLayout mDrawerLayout;
    private Context context = this;

    Toolbar toolbar;

    Poem_view main_poem;

    View_Suggested_Poem_Fragment suggested_poem_fragment;
    Like_List_Fragment like_list_fragment;
    Poem_Written_By_Me_Fragment poem_written_by_me_fragment;

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

        //네비게이션
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        suggested_poem_fragment = new View_Suggested_Poem_Fragment();
        like_list_fragment = new Like_List_Fragment();
        poem_written_by_me_fragment = new Poem_Written_By_Me_Fragment();

        getSupportFragmentManager().beginTransaction().add(R.id.poem_view, like_list_fragment).commit();



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
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        menuItem.setChecked(true);
        mDrawerLayout.closeDrawers();

        int id = menuItem.getItemId();
        String title = menuItem.getTitle().toString();

        if(id == R.id.nav_suggestionlist){
            Toast.makeText(context, title + "클릭 : nav_suggestionlist", Toast.LENGTH_SHORT).show();
            //onFragmentSelected(0,null);
        }
        else if(id == R.id.nav_likelist){
            Toast.makeText(context, title + "클릭 : nav_likelist", Toast.LENGTH_SHORT).show();
            //onFragmentSelected(1,null);
        }
        else if(id == R.id.nav_writtedlist){
            Toast.makeText(context, title + "클릭 :nav_writtedlist", Toast.LENGTH_SHORT).show();
            //onFragmentSelected(2,null);
        }

        mDrawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onFragmentSelected(int position, Bundle bundle) {
        Fragment curFragmnet = null;

        if (position==0) {
            curFragmnet = suggested_poem_fragment;
            toolbar.setTitle("추천 목록");
        } else if (position==1) {
            curFragmnet = like_list_fragment;
            toolbar.setTitle("좋아요 목록");
        } else if (position==2) {
            curFragmnet = poem_written_by_me_fragment;
            toolbar.setTitle("내가 쓴 시");
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.poem_view,curFragmnet).commit();
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

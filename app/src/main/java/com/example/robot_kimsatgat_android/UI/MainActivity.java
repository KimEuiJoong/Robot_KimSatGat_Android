package com.example.robot_kimsatgat_android.UI;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.robot_kimsatgat_android.GlobalApplication;
import com.example.robot_kimsatgat_android.R;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    Questionnaire1 Q1 = (Questionnaire1)Questionnaire1._Questionnaire1;
    Questionnaire2 Q2 = (Questionnaire2)Questionnaire2._Questionnaire2;

    private DrawerLayout mDrawerLayout;
    private Context context = this;
    private FragmentManager supportFragmentManager;

    private AppBarConfiguration mAppBarConfiguration;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        GlobalApplication globalApplication = (GlobalApplication)getApplication();
        String user_name = globalApplication.getName();
        supportFragmentManager = getSupportFragmentManager();
        Q1.finish();
        Q2.finish();

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false); // 기존 title 지우기
        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼 만들기
        actionBar.setHomeAsUpIndicator(R.drawable.hamburger); //뒤로가기 버튼 이미지 지정



        //Sample_poem_Adapter adapter = new Sample_poem_Adapter();
        //NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);


        //mAppBarConfiguration = new AppBarConfiguration.Builder(
        //        R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
        //        .setOpenableLayout(drawer)
        //        .build();

        NavHostFragment navHostFragment = (NavHostFragment) supportFragmentManager.findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();
        NavigationView navView = findViewById(R.id.nav_view);
        NavigationUI.setupWithNavController(navView,navController);

        View headerView = navView.getHeaderView(0);
        TextView drawerNameText = (TextView)headerView.findViewById(R.id.drawer_name_textView);
        drawerNameText.setText(user_name);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        mAppBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph())
                .setOpenableLayout(mDrawerLayout)
                .build();

        //ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        //        this,mDrawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        //mDrawerLayout.addDrawerListener(toggle);
        //toggle.syncState();



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ // 왼쪽 상단 버튼 눌렀을 때
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            }
        }
        NavController navController = Navigation.findNavController(this,R.id.nav_host_fragment);
        Log.i(TAG,Integer.toString(item.getItemId()));
        return NavigationUI.onNavDestinationSelected(item, navController) ||
                super.onOptionsItemSelected(item);
    }

}

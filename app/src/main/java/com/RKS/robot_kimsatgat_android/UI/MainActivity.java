package com.RKS.robot_kimsatgat_android.UI;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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

import com.RKS.robot_kimsatgat_android.GlobalApplication;
import com.RKS.robot_kimsatgat_android.R;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Toast toast;
    private DrawerLayout mDrawerLayout;
    private Context context = this;

    public FragmentManager supportFragmentManager;

    // 마지막으로 뒤로가기 버튼을 눌렀던 시간 저장
    private long backKeyPressedTime = 0;

    private AppBarConfiguration mAppBarConfiguration;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        // kakao 정보 가져오기.
        GlobalApplication globalApplication = (GlobalApplication)getApplication();
        String user_name = globalApplication.getName();
        supportFragmentManager = getSupportFragmentManager();

        // toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false); // 기존 title 지우기
        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼 만들기
        actionBar.setHomeAsUpIndicator(R.drawable.hamburger); //뒤로가기 버튼 이미지 지정


        // 추천 시 화면 설정, 네비게이션 뷰 설정
        NavHostFragment navHostFragment = (NavHostFragment) supportFragmentManager.findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();

        NavigationView navView = findViewById(R.id.nav_view);
        NavigationUI.setupWithNavController(navView,navController);

        // 네비게이션 뷰의 사용자 이름 설정
        View headerView = navView.getHeaderView(0);
        TextView drawerNameText = (TextView)headerView.findViewById(R.id.drawer_name_textView);
        drawerNameText.setText(user_name);

        // 드로우어 레이아웃 설정. main.xml의 id = drawer_layout
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mAppBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph())
                .setOpenableLayout(mDrawerLayout)
                .build();


    }


    @Override
    public void onBackPressed() {
         super.onBackPressed();
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

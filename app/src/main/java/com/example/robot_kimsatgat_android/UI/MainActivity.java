package com.example.robot_kimsatgat_android.UI;

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
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.robot_kimsatgat_android.GlobalApplication;
import com.example.robot_kimsatgat_android.R;
import com.example.robot_kimsatgat_android.UI.Views.CommentRecyclerFragment;
import com.example.robot_kimsatgat_android.UI.Views.View_LikeList;
import com.example.robot_kimsatgat_android.UI.Views.View_Main;
import com.example.robot_kimsatgat_android.UI.Views.View_MyPoem;
import com.example.robot_kimsatgat_android.UI.Views.View_Suggested_Poem;
import com.google.android.material.navigation.NavigationView;

import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private DrawerLayout mDrawerLayout;
    private Context context = this;
    private FragmentManager supportFragmentManager;

    // 마지막으로 뒤로가기 버튼을 눌렀던 시간 저장
    private long backKeyPressedTime = 0;
    // 첫 번째 뒤로가기 버튼을 누를때 표시
    private Toast toast;

    private AppBarConfiguration mAppBarConfiguration;

    // 프래그먼트 요소
    public static CommentRecyclerFragment view_poem_withcomments_fragment;
    public static View_Main view_poem_fragment;
    public static View_Suggested_Poem view_suggested_poem_fragment;
    public static View_LikeList view_likeList_fragment;
    public static View_MyPoem view_mypoem_fragment;

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

        // 프래그먼트 뒤로가기 기능 구현중...?
        // 앞에 supportFragmentManager가 manager
//        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
//
//        view_poem_fragment = new View_Main();
//        view_poem_withcomments_fragment = new CommentRecyclerFragment();
//
//        view_suggested_poem_fragment = new View_Suggested_Poem();
//        view_likeList_fragment = new View_LikeList();
//        view_mypoem_fragment = new View_MyPoem();
//
//        fragmentTransaction.replace(R.id.nav_host_fragment, view_poem_withcomments_fragment);
//        fragmentTransaction.addToBackStack(null);
//
//        // Commit the transaction
//        fragmentTransaction.commit();


    }

    @Override
    public void onBackPressed() {
        // 기존 뒤로가기 버튼의 기능을 막기위해 주석처리 또는 삭제
        // super.onBackPressed();

        // 마지막으로 뒤로가기 버튼을 눌렀던 시간에 2초를 더해 현재시간과 비교 후
        // 마지막으로 뒤로가기 버튼을 눌렀던 시간이 2초가 지났으면 Toast Show
        // 2000 milliseconds = 2 seconds
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            toast = Toast.makeText(this, "\'뒤로\' 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        // 마지막으로 뒤로가기 버튼을 눌렀던 시간에 2초를 더해 현재시간과 비교 후
        // 마지막으로 뒤로가기 버튼을 눌렀던 시간이 2초가 지나지 않았으면 종료
        // 현재 표시된 Toast 취소
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            finish();
            toast.cancel();
        }
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

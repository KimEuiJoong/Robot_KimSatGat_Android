package com.example.robot_kimsatgat_android;

import android.content.ContentValues;
import android.content.Context;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.robot_kimsatgat_android.DB.Poem_Write;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private Context context = this;

    private TextView textView_poem_title;
    private TextView textView_poet;
    private TextView textview_poet_View;

    private JSONObject jsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false); // 기존 title 지우기
        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼 만들기
        actionBar.setHomeAsUpIndicator(R.drawable.robotkim); //뒤로가기 버튼 이미지 지정

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

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
                    Toast.makeText(context, title + "클릭 : nav_suggestionlist", Toast.LENGTH_SHORT).show();
                }
                else if(id == R.id.nav_likelist){
                    // 화면 전환 코드
                    Toast.makeText(context, title + "클릭 : nav_likelist", Toast.LENGTH_SHORT).show();
                }
                else if(id == R.id.nav_writtedlist){
                    // 화면 전환 코드
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

        textView_poem_title = (TextView) findViewById(R.id.textView_poem_title);
        textView_poet = (TextView) findViewById(R.id.textView_poet);
        textview_poet_View = (TextView) findViewById(R.id.textview_poet_View);

        String url = "https://rest.robotkimsatgat.p-e.kr/poems/2?format=json";
        NetworkTask networkTask = new NetworkTask(url, null);
        networkTask.execute();


//        String url_post = "https://rest.robotkimsatgat.p-e.kr/poems/2";
//        try {
//            jsonObject = new JSONObject();
//            jsonObject.accumulate("condt", "SET ");
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        NetworkTask_POST networkTask_POST = new NetworkTask_POST(url_post,jsonObject.toString());
//        networkTask_POST.execute();

    }

    // Navi
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

    // NetworkTask
    public class NetworkTask extends AsyncTask<Void, Void, String> {

        private String url;
        private ContentValues values;


        public NetworkTask(String url, ContentValues values) {

            this.url = url;
            this.values = values;
        }

        @Override
        protected String doInBackground(Void... params) {

            String result; // 요청 결과를 저장할 변수.
            RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
            result = requestHttpURLConnection.request(url, values); // 해당 URL로 부터 결과물을 얻어온다.

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject json = new JSONObject(s);
                textView_poem_title.setText(json.getString("title"));
                textView_poet.setText(json.getString("writer"));
                textview_poet_View.setText(json.getString("content"));


                Toast.makeText(getApplicationContext(), "시 데이터를 불러오는데 성공했습니다.", Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                Toast.makeText(getApplicationContext(), "시 데이터를 불러오는데 실패했습니다.", Toast.LENGTH_SHORT).show();

                e.printStackTrace();
            }
        }

    } //NetworkTask

    // 값을 수정할때
    public class NetworkTask_POST extends AsyncTask<Void, Void, String> {

        private String url;
        private String json;

        public NetworkTask_POST(String url, String json) {
            this.url = url;
            this.json = json;
        }

        @Override
        protected String doInBackground(Void... params) {

            String result; // 요청 결과를 저장할 변수.
            RequestHttpURLConnection_POST requestHttpURLConnection = new RequestHttpURLConnection_POST();
            //RequestHttpURLCoonnection_POST 클래스를 통해 POST방식 사용
            result = requestHttpURLConnection.request(url, json); // 해당 URL로 부터 결과물을 얻어온다.

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //doInBackground()로 부터 리턴된 값이 onPostExecute()의 매개변수로 넘어오므로 s를 출력한다.\
        }

    } //NetworkTask_POST


} // MainActivity


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

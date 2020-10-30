package com.example.robot_kimsatgat_android.DB;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.robot_kimsatgat_android.Login.Login;
import com.example.robot_kimsatgat_android.Questionnaire.Questionnaire1;
import com.example.robot_kimsatgat_android.R;
import com.example.robot_kimsatgat_android.RequestHttpURLConnection_POST;

import org.json.JSONObject;

public class Poem_Write extends AppCompatActivity {
    private JSONObject jsonObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poem_write);


        Button btn_poem_save= findViewById(R.id.btn_poem_save);
        btn_poem_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                String url_post = "https://rest.robotkimsatgat.p-e.kr/comment/";
//                try {
//                    jsonObject = new JSONObject();
//                    jsonObject.getString("너무 좋아요!");
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//
//                NetworkTask_POST networkTask_POST = new NetworkTask_POST(url_post,jsonObject.toString());
//                networkTask_POST.execute();

                Toast.makeText(Poem_Write.this, "저장되었습니다.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }

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





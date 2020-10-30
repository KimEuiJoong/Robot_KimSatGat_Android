//package com.example.robot_kimsatgat_android;
//
//import android.os.Bundle;
//import android.view.View;
//import android.view.Menu;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.LinearLayout;
//import android.widget.ListView;
//
//import com.google.android.material.floatingactionbutton.FloatingActionButton;
//import com.google.android.material.snackbar.Snackbar;
//import com.google.android.material.navigation.NavigationView;
//
//import androidx.navigation.NavController;
//import androidx.navigation.Navigation;
//import androidx.navigation.ui.AppBarConfiguration;
//import androidx.navigation.ui.NavigationUI;
//import androidx.drawerlayout.widget.DrawerLayout;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;
//
//public class question_activity extends AppCompatActivity {
//    LinearLayout first_question;
//
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate((savedInstanceState));
//        setContentView(R.layout.question_first);
//
//        first_question = findViewById(R.id.first_question);
//        second_question = findViewById(R.id.second_question);
//    }
//
//    public void next_to_second_question_button_Clicked(View v) {
//        change_layout();
//    }
//
//    private void change_layout() {
//        first_question.setVisibility(View.INVISIBLE);
//        second_question.setVisibility(View.VISIBLE);
//    }
//
//    public void submit_button_Clicked(View v) {
//
//    }
//}

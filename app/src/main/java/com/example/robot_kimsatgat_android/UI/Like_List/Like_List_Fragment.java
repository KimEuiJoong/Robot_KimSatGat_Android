package com.example.robot_kimsatgat_android.UI.Like_List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.robot_kimsatgat_android.R;

public class Like_List_Fragment extends Fragment {

    private Like_List_ViewModel Like_List_Fragment1;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Like_List_Fragment1 =
                new ViewModelProvider(this).get(Like_List_ViewModel.class);
        View root = inflater.inflate(R.layout.fragment_view_likelist, container, false);
        final TextView textView = root.findViewById(R.id.textview_like_list);
        Like_List_Fragment1.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
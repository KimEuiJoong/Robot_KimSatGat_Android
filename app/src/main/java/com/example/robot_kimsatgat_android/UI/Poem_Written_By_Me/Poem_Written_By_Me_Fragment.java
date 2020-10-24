package com.example.robot_kimsatgat_android.UI.Poem_Written_By_Me;

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

public class Poem_Written_By_Me_Fragment extends Fragment {

    private Poem_Written_By_Me_ViewModel poemWrittenByMeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        poemWrittenByMeViewModel =
                new ViewModelProvider(this).get(Poem_Written_By_Me_ViewModel.class);
        View root = inflater.inflate(R.layout.fragment_view_mypoem, container, false);
        final TextView textView = root.findViewById(R.id.textview_poem_written_by_me);
        poemWrittenByMeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
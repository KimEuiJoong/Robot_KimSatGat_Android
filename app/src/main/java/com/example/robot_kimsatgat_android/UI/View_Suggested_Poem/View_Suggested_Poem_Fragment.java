package com.example.robot_kimsatgat_android.UI.View_Suggested_Poem;

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

public class View_Suggested_Poem_Fragment extends Fragment {

    private View_Suggested_Poem_ViewModel View_Suggested_Poem_Fragment;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View_Suggested_Poem_Fragment =
                new ViewModelProvider(this).get(View_Suggested_Poem_ViewModel.class);
        View root = inflater.inflate(R.layout.fragment_view_suggestedpoem, container, false);
        final TextView textView = root.findViewById(R.id.textview_view_suggested_poem);
        View_Suggested_Poem_Fragment.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
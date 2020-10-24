package com.example.robot_kimsatgat_android.UI.View_Suggested_Poem;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class View_Suggested_Poem_ViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public View_Suggested_Poem_ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
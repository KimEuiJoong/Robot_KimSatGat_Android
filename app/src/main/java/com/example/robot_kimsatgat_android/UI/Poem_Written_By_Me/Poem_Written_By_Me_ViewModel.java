package com.example.robot_kimsatgat_android.UI.Poem_Written_By_Me;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Poem_Written_By_Me_ViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public Poem_Written_By_Me_ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
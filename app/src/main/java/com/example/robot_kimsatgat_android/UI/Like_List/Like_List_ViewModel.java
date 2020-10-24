package com.example.robot_kimsatgat_android.UI.Like_List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Like_List_ViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public Like_List_ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
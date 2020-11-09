package com.example.robot_kimsatgat_android.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.robot_kimsatgat_android.Server.ParamClasses.RecvLikeData;
import com.example.robot_kimsatgat_android.Server.ParamClasses.RecvPoemBriefData;
import com.example.robot_kimsatgat_android.Server.PoemServer;

import java.util.List;

public class ViewModel_Main extends ViewModel {
    private PoemServer poemServer;
    public ViewModel_Main(){
        poemServer = PoemServer.getPoemServer();
    }
    public LiveData<List<RecvPoemBriefData>> getMyPoemList(){
        return poemServer.getMyPoemList();
    }
    public LiveData<List<RecvPoemBriefData>> getLikeList(){
        return poemServer.getMyLikeList();
    }
    public LiveData<RecvLikeData> getLikeNum(int poem_id) {return poemServer.getLike(poem_id);}
}

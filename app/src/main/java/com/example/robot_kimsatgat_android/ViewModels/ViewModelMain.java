package com.example.robot_kimsatgat_android.ViewModels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.robot_kimsatgat_android.Server.ParamClasses.RecvLikeData;
import com.example.robot_kimsatgat_android.Server.ParamClasses.RecvPoemBriefData;
import com.example.robot_kimsatgat_android.Server.PoemServer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

interface getListCommand{
    LiveData<List<RecvPoemBriefData>> run();
}

public class ViewModelMain extends ViewModel {
    private static final String TAG = "ViewModelMain";
    private PoemServer poemServer;
    private Map<String,getListCommand> listCommandMap;
    public ViewModelMain(){
        poemServer = PoemServer.getPoemServer();
        listCommandMap = new HashMap<>();
        listCommandMap.put("MyPoemList", () -> poemServer.getMyPoemList());
        listCommandMap.put("LikeList", () -> poemServer.getMyPoemList());
        listCommandMap.put("MyRecommendList", () -> poemServer.getMyRecommendList());
    }
    public LiveData<List<RecvPoemBriefData>> getList(String ListName){
        if(!listCommandMap.containsKey(ListName)){
            Log.e(TAG,"ListName " + ListName + "not Exists");
            return null;
        }
        return listCommandMap.get(ListName).run();
    }
    public LiveData<List<RecvPoemBriefData>> getMyPoemList(){
        return poemServer.getMyPoemList();
    }
    public LiveData<List<RecvPoemBriefData>> getLikeList(){
        return poemServer.getMyLikeList();
    }
    public LiveData<List<RecvPoemBriefData>> getMyRecommendList(){ return poemServer.getMyRecommendList(); }
    public LiveData<RecvLikeData> getLikeNum(int poem_id) {return poemServer.getLike(poem_id);}
}

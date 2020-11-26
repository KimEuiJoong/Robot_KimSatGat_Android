package com.example.robot_kimsatgat_android.ViewModels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.robot_kimsatgat_android.Server.ParamClasses.RecvCommentData;
import com.example.robot_kimsatgat_android.Server.ParamClasses.RecvLikeData;
import com.example.robot_kimsatgat_android.Server.ParamClasses.RecvPoemBriefData;
import com.example.robot_kimsatgat_android.Server.ParamClasses.RecvPoemData;
import com.example.robot_kimsatgat_android.Server.PoemServer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

interface getListCommand{
    LiveData<List<RecvPoemBriefData>> run();
}

public class ViewModelMain extends ViewModel {
    /**
     *  서버에서 데이터를 얻기위해 사용해야하는 클래스.
     *  사용법:
     *      액티비티나 프래그먼트 안에서
     *      ViewModelMain viewModelMain = new ViewModelProvider(this).get(ViewModelMain.class);
     *      으로 뷰모델 인스턴스를 얻어준다.
     *
     *      그리고 다음과 같이 작성하면 된다.
     *      viewModelMain.사용할_함수_이름().observe(getViewLifecycleOwner(), 얻어온_데이터 -> {
     *          //데이터를 얻는게 성공하고 나서 할 동작.
     *      };
     *
     *      예시로,
     *      Poem poem = new Poem();
     *      viewModelMain.getPoem().observe(getViewLifecycleOwner(), poemData -> {
     *          poem.id = poemData.id;
     *          poem.poem_name = poemData.title;
     *          ...
     *      };
     *      과 같이 작성하면 된다.
     *      얻어온 데이터의 이름은 사용하기 편한대로 지어도 상관없다.
     *      예시:
     *      Poem poem = new Poem();
     *      viewModelMain.getPoem().observe(getViewLifecycleOwner(), apple -> {
     *          poem.id = apple.id;
     *          poem.poem_name = apple.title;
     *          ...
     *      };
     *
     */
    private static final String TAG = "ViewModelMain";
    private PoemServer poemServer;
    private Map<String,getListCommand> listCommandMap;
    public ViewModelMain(){
        poemServer = PoemServer.getPoemServer();
        listCommandMap = new HashMap<>();
        listCommandMap.put("MyPoemList", () -> poemServer.getMyPoemList());
        listCommandMap.put("LikeList", () -> poemServer.getMyLikeList());
        listCommandMap.put("MyRecommendList", () -> poemServer.getMyRecommendList());
//        listCommandMap.put("Comments", () -> poemServer.getComments(poem_id));
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
    public LiveData<RecvPoemData> getPoem(int poem_id) {return poemServer.getPoem(poem_id);}

}

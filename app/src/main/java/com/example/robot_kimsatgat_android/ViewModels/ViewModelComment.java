package com.example.robot_kimsatgat_android.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.robot_kimsatgat_android.Server.ParamClasses.RecvCommentData;
import com.example.robot_kimsatgat_android.Server.ParamClasses.RecvPoemBriefData;
import com.example.robot_kimsatgat_android.Server.PoemServer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ViewModelComment extends ViewModel {
    private static final String TAG = "ViewModelComment";
    private PoemServer poemServer;
    private Map<String,getListCommand> listCommandMap_Comment;
    public ViewModelComment(int poem_id){
        poemServer = PoemServer.getPoemServer();
        listCommandMap_Comment = new HashMap<>();
        listCommandMap_Comment.put("Comments", () -> poemServer.getComments(poem_id);
    }
}

package com.RKS.robot_kimsatgat_android.Server.ParamClasses;

import java.util.ArrayList;

public class ReqPoemData {
    public ReqPoemData(String Title, String Content, ArrayList<String> Tag){
        title = Title;
        content = Content;
        tag = Tag;
    }
    String title;
    String content;
    ArrayList<String> tag;
}

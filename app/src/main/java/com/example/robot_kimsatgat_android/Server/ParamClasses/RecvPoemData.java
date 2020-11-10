package com.example.robot_kimsatgat_android.Server.ParamClasses;

public class RecvPoemData implements Cloneable {
    public int id;
    public String title;
    public String writer;
    public String content;
    public int likenum;
    public boolean like;
    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
}

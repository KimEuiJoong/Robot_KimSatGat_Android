package com.example.robot_kimsatgat_android.SampleData;

public class Poem {
    String main_text;
    String editor;
    String poem_name;

    boolean like;

    public Poem(String poem_name, String editor, String main_text, boolean like) {
        this.poem_name = poem_name;
        this.editor = editor;
        this.main_text = main_text;
        this.like = like;
    }

    public String getPoem_name(){
        return poem_name;
    }

    public void setPoem_name(String poem_name) { this.poem_name = poem_name; }

    public String getEditor(){
        return editor;
    }

    public void setEditor(String editor) { this.editor = editor; }

    public String getMain_text(){
        return main_text;
    }

    public void setMain_text(String main_text) { this.main_text = main_text; }

    public boolean getLike() { return like; }

    public void setLike(boolean like) { this.like = like; }
}
package com.RKS.robot_kimsatgat_android.SampleData;

public class Poem {
    public Integer id;
    public String main_text;
    public String editor;
    public String poem_name;
    public Integer likenum;
    public boolean like;
    public String comment_text;

    // parameter String [] comment_list
    public Poem(int id,String poem_name, String editor, String main_text,int likenum,boolean like) {
        this.id = id;
        this.poem_name = poem_name;
        this.editor = editor;
        this.main_text = main_text;
        this.likenum = likenum;
        this.like = like;
    }

    public int getId(){return id;}

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

    public String getLikenum_text(){return Integer.toString(likenum);}

    public void setLikenum(int likenum){this.likenum = likenum;}

    public boolean getLike(){return like;}

    public void setLike(boolean like){this.like= like;}

    public String getComment_text() {return comment_text;}
}

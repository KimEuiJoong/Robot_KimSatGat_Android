package com.example.robot_kimsatgat_android.SampleData;

public class Comment {
    public Integer poem_id;
    public String comment;

    public Comment(int poem_id,String comment)
    {
        this.poem_id=poem_id;
        this.comment=comment;
    }

    public int getPoem_id() { return poem_id; }

    public String getComment() { return comment; }

    public void setComment(String comment) { this.comment = comment; }
}

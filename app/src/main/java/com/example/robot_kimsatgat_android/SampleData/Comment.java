package com.example.robot_kimsatgat_android.SampleData;

public class Comment {

    public Integer comment_id;
    public String writer;
    public String content;

    public Comment(int comment_id,String writer,String content)
    {
        this.comment_id=comment_id;
        this.writer=writer;
        this.content=content;
    }

    public int getComment_id() { return comment_id; }


    public String getWriter() { return writer; }
    public String getContent() { return content; }


    public void setWriter(String content) { this.content = content; }
    public void setContent(String content) { this.content = content; }
}

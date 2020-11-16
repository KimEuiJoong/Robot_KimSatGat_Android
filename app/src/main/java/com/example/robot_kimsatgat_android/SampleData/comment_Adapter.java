package com.example.robot_kimsatgat_android.SampleData;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.robot_kimsatgat_android.R;
import com.example.robot_kimsatgat_android.Server.PoemServer;

import java.util.ArrayList;


public class comment_Adapter extends RecyclerView.Adapter<comment_Adapter.ViewHolder> {
    public ArrayList<Comment> items = new ArrayList<Comment>();
    PoemServer poemServer = PoemServer.getPoemServer();
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.comment_view,parent,false);
        ViewHolder viewHolder = new ViewHolder(itemView);

        Log.i("holder","created");
        return viewHolder;
    }

    @Override
    public long getItemId(int position) { return items.get(position).poem_id; }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Comment item = items.get(position);
    }
    @Override
    public int getItemCount() { return items.size(); }

    public void addComment(Comment item) { items.add(item); }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView comment;

        public ViewHolder (View itemView)
        {
            super (itemView);
            comment = itemView.findViewById(R.id.comment);
        }

        public void setItem(Comment item, int position) {
            comment.setText(item.getComment());
        }
    }
}

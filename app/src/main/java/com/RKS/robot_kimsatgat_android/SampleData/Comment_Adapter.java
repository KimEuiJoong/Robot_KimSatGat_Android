package com.RKS.robot_kimsatgat_android.SampleData;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.RKS.robot_kimsatgat_android.R;
import com.RKS.robot_kimsatgat_android.Server.PoemServer;

import java.util.ArrayList;


public class Comment_Adapter extends RecyclerView.Adapter<Comment_Adapter.ViewHolder> {
    private static final String TAG = "Comment_Adapter";
    public ArrayList<Comment> items = new ArrayList<>();
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
    public long getItemId(int position) {
        try {
            if(items == null){
                Log.i(TAG,"item list is null");
            }
            if(items.get(position) == null){
                Log.i(TAG,"item is null");
            }
            return items.get(position).comment_id;
        }catch(Exception e){
            Log.i(TAG,e.getMessage());
            return 0;
        }

    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Comment item = items.get(position);
        holder.setItem(item,position);
    }
    @Override
    public int getItemCount() { return items.size(); }

    public void addComment(Comment item) {
        for(Comment comment :items){
            if(comment.comment_id == item.comment_id){
                return;
            }
        }
        items.add(item);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView writer_TV;
        TextView content_TV;

        public ViewHolder (View itemView)
        {
            super (itemView);
            writer_TV = itemView.findViewById(R.id.comment_writer);
            content_TV = itemView.findViewById(R.id.comment_content);
        }

        public void setItem(Comment item, int position) {
            writer_TV.setText(item.getWriter());
            content_TV.setText(item.getContent());
            Log.i(TAG,"msg" + item.getContent());
        }
    }
}

package com.example.robot_kimsatgat_android.SampleData;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.robot_kimsatgat_android.R;
import com.example.robot_kimsatgat_android.Server.PoemServer;

import java.util.ArrayList;


public class comment_Adapter extends RecyclerView.Adapter<comment_Adapter.ViewHolder> {
    public ArrayList<Poem> items = new ArrayList<>();
    PoemServer poemServer = PoemServer.getPoemServer();
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.extend_poem_view,parent,false);
        ViewHolder viewHolder = new ViewHolder(itemView);

        Log.i("holder","created");
        return viewHolder;
    }

    @Override
    public long getItemId(int position) { return items.get(position).id; }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }
    @Override
    public int getItemCount() { return items.size(); }

    public void addComment(Poem item) { items.add(item); }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder (View itemView)
        {
            super (itemView);
            //view = itemView;
        }

        public void setItem(Poem item, int position) {

        }
    }
}

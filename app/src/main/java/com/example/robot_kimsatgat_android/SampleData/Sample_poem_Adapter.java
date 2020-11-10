package com.example.robot_kimsatgat_android.SampleData;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.robot_kimsatgat_android.R;

import java.util.ArrayList;


public class Sample_poem_Adapter extends RecyclerView.Adapter<Sample_poem_Adapter.ViewHolder>{
    ArrayList<Poem> items = new ArrayList<Poem>();
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.poem_view, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Poem item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addPoem(Poem item) { items.add(item); }


    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView poem_title_view;
        TextView poem_writer_view;
        TextView poem_main_view;

        boolean like;

        Button Like_btn;
        Button Not_Like_btn;

        public ViewHolder(View itemView)
        {
            super (itemView);

            poem_title_view = itemView.findViewById(R.id.poem_title);
            poem_writer_view = itemView.findViewById(R.id.poem_writer);
            poem_main_view = itemView.findViewById(R.id.poem_main);

            Like_btn = itemView.findViewById(R.id.like_button);
            Not_Like_btn = itemView.findViewById(R.id.not_like_button);
        }

        public void setItem(Poem item)
        {
            poem_title_view.setText(item.getPoem_name());
            poem_writer_view.setText(item.getEditor());
            poem_main_view.setText(item.getMain_text());

            like = item.getLike();

            if(like == true) {
                Not_Like_btn.setVisibility(View.VISIBLE);
                Like_btn.setVisibility(View.INVISIBLE);
            } else {
                Like_btn.setVisibility(View.VISIBLE);
                Not_Like_btn.setVisibility(View.INVISIBLE);
            }
        }
    }
}

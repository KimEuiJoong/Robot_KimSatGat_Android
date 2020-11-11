package com.example.robot_kimsatgat_android.SampleData;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.robot_kimsatgat_android.R;
import com.example.robot_kimsatgat_android.Server.ParamClasses.RecvLikeData;
import com.example.robot_kimsatgat_android.Server.PoemServer;
import com.example.robot_kimsatgat_android.ViewModels.ViewModelMain;

import java.util.ArrayList;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;


public class PoemRecyclerAdapter extends RecyclerView.Adapter<PoemRecyclerAdapter.ViewHolder>{
    public ArrayList<Poem> items = new ArrayList<>();
    PoemServer poemServer = PoemServer.getPoemServer();
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.poem_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);

        Log.i("holder","created");
        return viewHolder;
    }
    @Override
    public long getItemId(int position){
        return items.get(position).id;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Poem item = items.get(position);
        holder.setItem(item,position);
        Log.i("poemAdapter",System.identityHashCode(this)+"binded pos" + Integer.toString(position));
        holder.Ibtn_poemlike.setOnClickListener(new View.OnClickListener() {
            Poem item = holder.poem;
            @Override
            public void onClick(View view) {
                if(!item.like){
                    item.like = true;
                    holder.Ibtn_poemlike.setImageResource(R.drawable.heart_filled_drawable);
                    poemServer.postLike(item.id, new Function0<Void>() {
                        @Override
                        public Void invoke() {
                            poemServer.getLike(item.id, new Function1<RecvLikeData, Void>() {
                                @Override
                                public Void invoke(RecvLikeData recvLikeData) {
                                    item.likenum = recvLikeData.likenum;
                                    holder.poem_likenum_view.setText(Integer.toString(item.likenum));
                                    return null;
                                }
                            });
                            return null;
                        }
                    });
                }else{
                    item.like = false;
                    holder.Ibtn_poemlike.setImageResource(R.drawable.heart_drawable);
                    poemServer.deleteLike(item.id, new Function0<Void>() {
                        @Override
                        public Void invoke() {
                            poemServer.getLike(item.id, new Function1<RecvLikeData, Void>() {
                                @Override
                                public Void invoke(RecvLikeData recvLikeData) {
                                    item.likenum = recvLikeData.likenum;
                                    holder.poem_likenum_view.setText(Integer.toString(item.likenum));
                                    return null;
                                }
                            });
                            return null;
                        }
                    });
                }
                Log.i("poemAdapter","clicked");
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addPoem(Poem item) {
        items.add(item);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        Poem poem;
        TextView poem_title_view;
        TextView poem_writer_view;
        TextView poem_main_view;
        TextView poem_likenum_view;
        View view;
        ViewModelMain viewModelMain;
        ImageButton Ibtn_poemlike;
        FragmentActivity owner;
        Function1<Integer,Void> update;
        public ViewHolder(View itemView)
        {
            super (itemView);
            view = itemView;
            owner = (FragmentActivity) view.getContext();
            poem_title_view = view.findViewById(R.id.poem_title);
            poem_writer_view = view.findViewById(R.id.poem_writer);
            poem_main_view = view.findViewById(R.id.poem_main);
            poem_likenum_view = view.findViewById(R.id.like_count);
            Ibtn_poemlike = itemView.findViewById(R.id.likeIButton);
            viewModelMain = new ViewModelProvider(owner).get(ViewModelMain.class);
        }

        public void setItem(Poem item,int position)
        {
            poem = item;

            //Log.i("poemAdapter","ViewHolded: "+ System.identityHashCode(this));
            poem_title_view.setText(item.getPoem_name());
            poem_writer_view.setText(item.getEditor());
            poem_main_view.setText(item.getMain_text());
            poem_likenum_view.setText(item.getLikenum_text());
            if(item.like){
                Ibtn_poemlike.setImageResource(R.drawable.heart_filled);
                //Ibtn_poemlike.setScaleType(ImageView.ScaleType.FIT_XY);
            }else{
                Ibtn_poemlike.setImageResource(R.drawable.heart);
                //Ibtn_poemlike.setScaleType(ImageView.ScaleType.FIT_XY);
            }
        }
        void updateLikeNum(Poem item,int position){
            viewModelMain.getLikeNum(item.id).observe(owner,recvLikeData->{
                item.likenum = recvLikeData.likenum;
                update.invoke(position);
            });
        }
    }
}

package com.example.todolistroom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolistroom.ItemClick;
import com.example.todolistroom.R;
import com.example.todolistroom.database.entity.Task;
import com.example.todolistroom.databinding.ItemListBinding;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Task> tasks;
    private ItemClick itemClick;

    public MyAdapter(Context context, ArrayList<Task> tasks,ItemClick itemClick) {
        this.context = context;
        this.tasks = tasks;
        this.itemClick=itemClick;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemListBinding itemListBinding= DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.item_list,parent,false);
        return new MyViewHolder(itemListBinding,itemClick);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Task task=tasks.get(position);
        holder.itemListBinding.setTask(task);

    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ItemListBinding itemListBinding;

        public MyViewHolder( ItemListBinding itemListBinding,ItemClick itemClick) {
            super(itemListBinding.getRoot());
            this.itemListBinding=itemListBinding;


            itemListBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos=getAdapterPosition();
                    if(itemListBinding!=null&&pos!=RecyclerView.NO_POSITION){
                        itemClick.onClick(pos);
                    }
                }
            });
            itemListBinding.getRoot().setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos=getAdapterPosition();
                    if(itemClick!=null&&pos!=RecyclerView.NO_POSITION){
                        itemClick.onLongClick(pos);
                    }
                    return false;
                }
            });

        }
    }

}

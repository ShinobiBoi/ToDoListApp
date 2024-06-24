package com.example.todolistroom;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.todolistroom.adapter.MyAdapter;

import com.example.todolistroom.database.entity.Task;
import com.example.todolistroom.databinding.ActivityMainBinding;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemClick {
    MyAdapter adapter;

    RecyclerView recyclerView;

    ArrayList<Task> tasks=new ArrayList<>();

    ActivityMainBinding activityMainBinding;
    MainClickHandler handler;

    ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        viewModel= new ViewModelProvider(this).get(ViewModel.class);
        activityMainBinding= DataBindingUtil.setContentView(this,R.layout.activity_main);

        handler=new MainClickHandler(this);
        activityMainBinding.setHandler(handler);


        viewModel.getTasks().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> taskss) {
                if (taskss != null) {
                    tasks.clear(); // Clear existing tasks
                    tasks.addAll(taskss); // Update tasks with new data
                }
                adapter.notifyDataSetChanged(); // Notify adapter of data change

            }
        });

        adapter=new MyAdapter(this,tasks,this);


        recyclerView=activityMainBinding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);



    }

    @Override
    public void onLongClick(int pos) {
        Task task=tasks.get(pos);

        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Delete "+task.getName()+"?");
        builder.setMessage("Are you sure you want to delete "+task.getName()+"?");
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
              viewModel.deleteTask(task);


                Intent intent=new Intent(MainActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();

    }



    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Confirm Exit")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finishAffinity();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing because the user canceled the action
                    }
                })
                .show();
    }



    @Override
    public void onClick(int pos) {
        Task task=tasks.get(pos);
        Intent intent=new Intent(MainActivity.this,ViewTaskActivity.class);
        intent.putExtra("task",task);
        startActivity(intent);
    }


    public class MainClickHandler{
        Context context;


        public MainClickHandler(Context context) {
            this.context = context;
        }

        public void fabButton(View view){
            Intent intent=new Intent(MainActivity.this,AddTaskActivity.class);
            startActivity(intent);

        }
    }
}
package com.example.todolistroom;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.todolistroom.database.entity.Task;

import com.example.todolistroom.databinding.ActivityViewTaskBinding;

public class ViewTaskActivity extends AppCompatActivity {

    ViewClickHandler handler;
    Task task;

    ActionBar bar;
    ViewModel viewModel;

   ActivityViewTaskBinding activityViewTaskBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_task);

        Intent intent=getIntent();
        task= (Task) intent.getSerializableExtra("task");

        bar=getSupportActionBar();
        bar.setTitle(task.getName());

        viewModel=new ViewModelProvider(this).get(ViewModel.class);
        activityViewTaskBinding= DataBindingUtil.setContentView(this,R.layout.activity_view_task);





        activityViewTaskBinding.setTask(task);

        handler=new ViewClickHandler(this);
        activityViewTaskBinding.setHandler(handler);




    }


    public class ViewClickHandler{

        Context context;

        public ViewClickHandler(Context context) {
            this.context = context;
        }


        public void updateButton(View view){
            if (task.getName().trim().isEmpty()||task.getDesc().trim().isEmpty())
                Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
            else{
                viewModel.updateTask(task);
                Toast.makeText(context, "Updated Successfully", Toast.LENGTH_SHORT).show();
            }



            Intent home=new Intent(ViewTaskActivity.this,MainActivity.class);
            startActivity(home);
            finish();
        }
        public void deleteButton(View view){
            AlertDialog.Builder builder=new AlertDialog.Builder(ViewTaskActivity.this);
            builder.setTitle("Delete "+task.getName()+"?");
            builder.setMessage("Are you sure you want to delete "+task.getName()+"?");
            builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    viewModel.deleteTask(task);


                    Intent intent=new Intent(ViewTaskActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
            builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.create().show();

        }
    }
}
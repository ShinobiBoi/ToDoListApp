package com.example.todolistroom;

        import android.content.Context;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Toast;


        import androidx.activity.EdgeToEdge;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.databinding.DataBindingUtil;
        import androidx.lifecycle.ViewModelProvider;

        import com.example.todolistroom.database.entity.Task;
        import com.example.todolistroom.databinding.ActivityAddTaskBinding;

public class AddTaskActivity extends AppCompatActivity {


    ViewModel viewModel;
    ActivityAddTaskBinding activityAddTaskBinding;
    Task task=new Task();

    ClickHandler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_task);


        viewModel=new ViewModelProvider(this).get(ViewModel.class);
        activityAddTaskBinding= DataBindingUtil.setContentView(this,R.layout.activity_add_task);


        activityAddTaskBinding.setTask(task);


        handler=new ClickHandler(this);

        activityAddTaskBinding.setHandler(handler);




    }


    public class ClickHandler{
        Context context;

        public ClickHandler(Context context) {
            this.context = context;
        }

        public void addButton(View view){
            if (task.getName()==null||task.getDesc()==null)
                Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
            else{
                viewModel.insertTask(task);
                Toast.makeText(context, "Added Successfully", Toast.LENGTH_SHORT).show();
            }

            Intent intent=new Intent(context,MainActivity.class);
            startActivity(intent);
            finish();

        }


    }
}
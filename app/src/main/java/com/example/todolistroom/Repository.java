package com.example.todolistroom;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.todolistroom.database.TaskDAO;
import com.example.todolistroom.database.TaskDatabase;
import com.example.todolistroom.database.entity.Task;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {


    private TaskDAO taskDAO;




    public Repository(Application application){
        TaskDatabase db=TaskDatabase.getInstance(application);
        taskDAO=db.getTaskDAO();

    }

    public LiveData<List<Task>> getTasks() {
        return taskDAO.getAllTasks();
    }


    public void insertTask(Task task){
        ExecutorService service= Executors.newSingleThreadExecutor();
        service.execute(new Runnable() {
            @Override
            public void run() {
                taskDAO.insert(task);
            }
        });
    }
    public void updateTask(Task task){
        ExecutorService service= Executors.newSingleThreadExecutor();
        service.execute(new Runnable() {
            @Override
            public void run() {
                taskDAO.update(task);
            }
        });
    }
    public void deleteTask(Task task){
        ExecutorService service= Executors.newSingleThreadExecutor();
        service.execute(new Runnable() {
            @Override
            public void run() {
                taskDAO.delete(task);
            }
        });
    }


}

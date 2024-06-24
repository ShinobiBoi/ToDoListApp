package com.example.todolistroom;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.todolistroom.database.entity.Task;

import java.util.List;

public class ViewModel extends AndroidViewModel {

    private Repository repository;
    public LiveData<List<Task>> tasks;

    public ViewModel(@NonNull Application application) {
        super(application);
        repository=new Repository(application);
    }

    public LiveData<List<Task>> getTasks() {
        tasks=repository.getTasks();
        return tasks;
    }

    public void insertTask(Task task){
        repository.insertTask(task);
    }

    public void updateTask(Task task){
        repository.updateTask(task);
    }
    public void deleteTask(Task task){
        repository.deleteTask(task);
    }



}

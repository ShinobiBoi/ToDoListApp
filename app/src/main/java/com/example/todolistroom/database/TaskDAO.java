package com.example.todolistroom.database;

import androidx.lifecycle.LiveData;
import androidx.room.ColumnInfo;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.todolistroom.database.entity.Task;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface TaskDAO {



    @Insert
    public void insert(Task task);


    @Update
    public void update(Task task);

    @Delete
    public void delete(Task task);

    @Query("Select * from task order by rank desc")
    public LiveData<List<Task>>getAllTasks();

}

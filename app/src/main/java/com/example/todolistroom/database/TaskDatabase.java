package com.example.todolistroom.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.todolistroom.database.entity.Task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Task.class},version = 1)
public  abstract class TaskDatabase extends RoomDatabase {



    public abstract TaskDAO getTaskDAO();


    private static TaskDatabase instance;

    public static synchronized TaskDatabase getInstance(Context context){
        if (instance==null){

            instance= Room.databaseBuilder(context,TaskDatabase.class,"taskDB")
                    .fallbackToDestructiveMigration().addCallback(callback).build();

        }


        return instance;
    }


    private static RoomDatabase.Callback callback=new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            initialize();


        }
    };

    private static void initialize(){
        TaskDAO taskDAO=instance.getTaskDAO();

        Task task=new Task();
        task.setName("Welcome to my ToDoList app ❤\uFE0F");
        task.setDesc("Stay tuned for upcoming releases");
        task.setRank(5);
        ExecutorService service= Executors.newSingleThreadExecutor();
        service.execute(new Runnable() {
            @Override
            public void run() {
                taskDAO.insert(task);
            }
        });



    }


}



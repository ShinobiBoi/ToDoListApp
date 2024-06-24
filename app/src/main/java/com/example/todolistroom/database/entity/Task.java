package com.example.todolistroom.database.entity;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity()
public class Task extends BaseObservable implements Serializable {



    private String name;

    private String desc;

    @PrimaryKey(autoGenerate = true)
    private int id;

    private float rank;

    @Ignore
    public Task() {
    }

    public Task(String name, String desc, int id, float rank) {
        this.name = name;
        this.desc = desc;
        this.id = id;
        this.rank = rank;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);

    }

    @Bindable
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
        notifyPropertyChanged(BR.desc);
    }

    @Bindable
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public float getRank() {
        return rank;
    }

    public void setRank(float rank) {
        this.rank = rank;
        notifyPropertyChanged(BR.rank);
    }


}

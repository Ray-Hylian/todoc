package com.cleanup.todoc.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.cleanup.todoc.model.Task;

import java.util.List;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM tasks_table")
    LiveData<List<Task>> getTask();//récupère la liste des tâches

    @Insert
    long insertTask(Task task);//créer une nouvelle tâche à faire

    @Delete
    int deleteTask(Task task);//supprimer une tâche existante/ @Query ou @Delete
}

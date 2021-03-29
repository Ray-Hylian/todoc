package com.cleanupRB.todocByRB.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.cleanupRB.todocByRB.model.Task;

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

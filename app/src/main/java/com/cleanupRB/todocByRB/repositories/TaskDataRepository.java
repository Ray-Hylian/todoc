package com.cleanupRB.todocByRB.repositories;

import androidx.lifecycle.LiveData;

import com.cleanupRB.todocByRB.database.TaskDao;
import com.cleanupRB.todocByRB.model.Task;

import java.util.List;

public class TaskDataRepository {//récupère à partir du constructeur le dao qui est isolé pour que le viewmodel ne le manipule pas directement

    private final TaskDao taskDao;

    public TaskDataRepository(TaskDao taskDao) { this.taskDao = taskDao; }

    // --- GET ---

    public LiveData<List<Task>> getTask(){ return this.taskDao.getTask(); }

    // --- CREATE ---

    public void createTask(Task task){ taskDao.insertTask(task); }

    // --- DELETE ---
    public void deleteTask(Task task){ taskDao.deleteTask(task); }
}

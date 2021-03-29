package com.cleanupRB.todocByRB.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.annotation.Nullable;

import com.cleanupRB.todocByRB.model.Project;
import com.cleanupRB.todocByRB.model.Task;
import com.cleanupRB.todocByRB.repositories.ProjectDataRepository;
import com.cleanupRB.todocByRB.repositories.TaskDataRepository;

import java.util.List;
import java.util.concurrent.Executor;

public class TaskViewModel extends ViewModel { //fournit les données

    //repositories
    private final TaskDataRepository taskDataRepository;
    private final ProjectDataRepository projectDataRepository;
    private final Executor executor;//permet l'execution de méthodes en arrière plan

    //data
    @Nullable
    private LiveData<List<Project>> currentProject;

    public TaskViewModel(TaskDataRepository taskDataSource, ProjectDataRepository projectDataSource, Executor executor){
        this.taskDataRepository = taskDataSource;
        this.projectDataRepository = projectDataSource;
        this.executor = executor;
    }

    //initialisation du viewmodel dans onCreate
    public void init(){
        if (this.currentProject != null){
            return;
        }
        currentProject = projectDataRepository.getProject();
    }

    //for project
    public LiveData<List<Project>> getProject(){return this.currentProject;}

    //for task
    public LiveData<List<Task>> getTask(){return taskDataRepository.getTask();}

    public void createTask(Task task) {
        executor.execute(() -> {
            taskDataRepository.createTask(task);
        });
    }

    public void deleteTask(Task task) {
        executor.execute(() -> {
            taskDataRepository.deleteTask(task);
        });
    }
}

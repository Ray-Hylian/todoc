package com.cleanupRB.todocByRB.injection;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.cleanupRB.todocByRB.repositories.ProjectDataRepository;
import com.cleanupRB.todocByRB.repositories.TaskDataRepository;
import com.cleanupRB.todocByRB.ui.TaskViewModel;

import java.util.concurrent.Executor;


//même fonction que TaskViewModel
public class ViewModelFactory implements ViewModelProvider.Factory {

    private final TaskDataRepository taskDataSource;
    private final ProjectDataRepository projectDataSource;
    private final Executor executor;

    public ViewModelFactory(TaskDataRepository taskDataSource, ProjectDataRepository projectDataSource, Executor executor){
        this.taskDataSource = taskDataSource;
        this.projectDataSource = projectDataSource;
        this.executor = executor;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass){
        if(modelClass.isAssignableFrom(TaskViewModel.class)){
            return (T) new TaskViewModel(taskDataSource, projectDataSource, executor);
        }
        throw new IllegalArgumentException("Unknown ViewModel cass");
    }
}

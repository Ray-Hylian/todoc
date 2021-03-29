package com.cleanupRB.todocByRB.repositories;

import androidx.lifecycle.LiveData;

import com.cleanupRB.todocByRB.database.ProjectDao;
import com.cleanupRB.todocByRB.model.Project;

import java.util.List;

public class ProjectDataRepository { //récupère à partir du constructeur le dao qui est isolé pour que le viewmodel ne le manipule pas directement

    private final ProjectDao projectDao;

    public ProjectDataRepository(ProjectDao projectDao) { this.projectDao = projectDao; }

    // --- GET PROJECT ---
    public LiveData<List<Project>> getProject() { return this.projectDao.getProject(); }
}


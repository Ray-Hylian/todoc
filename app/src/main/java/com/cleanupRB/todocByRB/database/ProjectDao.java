package com.cleanupRB.todocByRB.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.cleanupRB.todocByRB.model.Project;

import java.util.List;

@Dao
public interface ProjectDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)//éviter doublon
    void createProject(Project project);//création nouveau projet

    @Query("SELECT * FROM project_table")
    LiveData<List<Project>> getProject();//récupération tous les projets
}

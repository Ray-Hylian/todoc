package com.cleanup.todoc;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.cleanup.todoc.database.TodocDatabase;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class DaoTest {

    // For data
    private TodocDatabase dataBase;;

    private static long PROJECT_ID = 1;
    private static Project PROJECT_DEMO = new Project(PROJECT_ID, "test", 0x2A0BF0);
    private static Task TASK_DEMO = new Task(PROJECT_ID, "test", 1620401415);

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    //création d'une instance placée dans la bdd
    @Before
    public void initDb() throws Exception {
        this.dataBase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                TodocDatabase.class).allowMainThreadQueries().build();
    }

    @After
    public void closeDb() throws Exception {
        dataBase.close();
    }

    @Test
    public void getTasksWhenNoTaskInserted() throws InterruptedException {
        //TEST
        List<Task> tasks = LiveDataTestUtil.getValue(this.dataBase.taskDao().getTask());
        assertTrue(tasks.isEmpty());
    }

    @Test
    public void insertAndGetTasks() throws InterruptedException {
        //BEFORE : adding a new task associated to a project
        this.dataBase.projectDao().createProject(PROJECT_DEMO);
        this.dataBase.taskDao().insertTask(TASK_DEMO);
        //TEST
        List<Task> tasks = LiveDataTestUtil.getValue(this.dataBase.taskDao().getTask());
        assertTrue(tasks.size() == 1);
    }

    @Test
    public void insertAndDeleteTask() throws InterruptedException {
        this.dataBase.projectDao().createProject(PROJECT_DEMO);
        this.dataBase.taskDao().insertTask(TASK_DEMO);

        Task tasksAdded = LiveDataTestUtil.getValue(this.dataBase.taskDao().getTask()).get(0);
        this.dataBase.taskDao().deleteTask(tasksAdded);
        //TEST
        List<Task> tasks = LiveDataTestUtil.getValue(this.dataBase.taskDao().getTask());
        assertTrue(tasks.isEmpty());
    }

    @Test
    public void insertAndGetProject() throws InterruptedException {
        //BEFORE : adding a new project
        this.dataBase.projectDao().createProject(PROJECT_DEMO);
        //TEST
        List<Project> projects = LiveDataTestUtil.getValue(this.dataBase.projectDao().getProject());
        assertTrue(projects.size() == 1);
    }
}

package com.example.android.todolist.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface TaskDao {

    /**
     * This Query is used for selecting. Returns Objects which are mapped.
     * */
    @Query("SELECT * FROM task ORDER BY priority ")
    List<TaskEntry> loadAllTask();

    /**
     * Inserts the given POJO into database.
     * */
    @Insert
    void InsertTask(TaskEntry taskEntry);

    /**
     * Replaces if there is a conflict
     * */
    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateTask(TaskEntry taskEntry);

    @Delete
    void deleteTask(TaskEntry taskEntry);

    @Query("SELECT * FROM task WHERE id = :id")
    TaskEntry loadTaskById(int id);

}

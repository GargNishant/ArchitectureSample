package com.example.android.todolist.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;


/**Creates a Table from the @POJO. Default name will be name of class. Can Override
 *
 * By default all the Variable will be associated with seperate columns of table, i.e. all variables
 * will create their own columns*/
@Entity(tableName = "task")
public class TaskEntry {

    /**Creates a primary Key for a Table. By Default auto-generation is off, but we can Override it*/
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String description;
    private int priority;
    @ColumnInfo(name = "updated_at")
    private Date updatedAt;


    /**@Ignore: We Add this if we want some of variable to be ignored and not be associated
     * with any column of the table
     *
     * Can Also be used in this way so that we can say to Room that we are going to Ignore this one.
     * As room can only use One Constructor.*/
    @Ignore
    public TaskEntry(String description, int priority, Date updatedAt) {
        this.description = description;
        this.priority = priority;
        this.updatedAt = updatedAt;
    }


    /**Using this constructor for Room so that it can read from Database as each entry will have
     * id*/
    public TaskEntry(int id, String description, int priority, Date updatedAt) {
        this.id = id;
        this.description = description;
        this.priority = priority;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}

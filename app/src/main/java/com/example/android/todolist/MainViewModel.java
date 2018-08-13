package com.example.android.todolist;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.android.todolist.database.AppDatabase;
import com.example.android.todolist.database.TaskEntry;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private LiveData<List<TaskEntry>> task;

    /**
     * Loading the List of tasks and adding to a LiveData Object.
     * Essentially caching all the entries in ViewModel.
     * */
    public MainViewModel(@NonNull Application application) {
        super(application);
        AppDatabase database = AppDatabase.getInstance(this.getApplication());

        /**
         * Returns a LiveData object of List of all the tasks in DB
         * */
        database.taskDao().loadAllTask();

    }

    public LiveData<List<TaskEntry>> getTask() {
        return task;
    }
}

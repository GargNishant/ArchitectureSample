package com.example.android.todolist;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.android.todolist.database.AppDatabase;


/**
 * By default, the ViewModel is incapable of taking parameters. By using
 * Factory classes, we can supply parameters to the ViewModel
 * */
public class AddTaskViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private final AppDatabase mDb;
    private final int mTaskId;

    public AddTaskViewModelFactory(AppDatabase mDb, int mTaskId){
        this.mTaskId = mTaskId;
        this.mDb = mDb;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        /**
         * Creates a new Instance of AddTaskViewModel and passes the
         * arguments to the method
         * */
        try{
            T newClassInstance = super.create(modelClass);
            if(newClassInstance instanceof AddTaskViewModel){
                ((AddTaskViewModel) newClassInstance).setDependencies(mTaskId,mDb);
            }
            return newClassInstance;
        } catch (Exception e){
            throw new RuntimeException("Cannot Create an instance of "+ modelClass,e);
        }
    }

}

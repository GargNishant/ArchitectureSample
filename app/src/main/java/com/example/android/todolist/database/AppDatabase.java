package com.example.android.todolist.database;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.util.Log;



/**
 * We have to declare the Entities, version, exportSchema, and if any Type Convertor as
 * Database should have Entities and Dao in it
 * */
@Database(entities = {TaskEntry.class}, version = 1, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {

    private static final String LOG_TAG = AppDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "todolist";
    private static AppDatabase _instance;


    public static AppDatabase getInstance(Context context) {
        if (_instance == null) {
            /**
             * For this search java docs
             * */
            synchronized (LOCK) {
                Log.d(LOG_TAG, "Creating new database instance");
                _instance = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, AppDatabase.DATABASE_NAME)
                        .build();
            }
        }
        Log.d(LOG_TAG, "Getting the database instance");

        /**
         * Returning the same object created earlier, thus we are getting the
         * same static object all the time
         * */
        return _instance;
    }
    public abstract TaskDao taskDao();
}

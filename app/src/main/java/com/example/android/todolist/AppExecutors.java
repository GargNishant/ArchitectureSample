package com.example.android.todolist;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


/**
 * This Class is temporary.
 *
 * Executors are used here to use the DB in background thread instead of UI thread.
 * It is a Singleton class.
 * */
public class AppExecutors {

    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static AppExecutors sInstance;

    /**
     * @diskIO: This executor is responsible to use the local DB or SQLite. We will have
     * only one diskIO thread throughout our application, thus making all the DB calls
     * sequential
     * @mainThread: This executor will be used update the UI and runs in UI Thread.
     * We will have only one mainThread throughout our app
     * @networkIO: This executor will be used for the network calls in the background.
     * In out Application we will have 3 threads in networkIO so that we can limit the
     * number of API calls at a time.
     * */
    private final Executor diskIO;
    private final Executor mainThread;
    private final Executor networkIO;

    private AppExecutors(Executor diskIO, Executor networkIO, Executor mainThread) {
        this.diskIO = diskIO;
        this.networkIO = networkIO;
        this.mainThread = mainThread;
    }

    public static AppExecutors getInstance() {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new AppExecutors(Executors.newSingleThreadExecutor(),
                        Executors.newFixedThreadPool(3),
                        new MainThreadExecutor());
            }
        }
        return sInstance;
    }

    public Executor diskIO() {
        return diskIO;
    }

    public Executor mainThread() {
        return mainThread;
    }

    public Executor networkIO() {
        return networkIO;
    }

    private static class MainThreadExecutor implements Executor {
        private Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable command) {
            mainThreadHandler.post(command);
        }
    }
}

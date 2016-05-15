package de.bunk;

import de.bunk.usecase.PostExecutionThread;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;

public class PostExecutionThreadAndroidMain implements PostExecutionThread {
    @Override
    public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }
}

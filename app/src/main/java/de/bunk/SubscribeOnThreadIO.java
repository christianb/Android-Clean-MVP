package de.bunk;

import de.bunk.usecase.SubscribeOnThread;
import rx.Scheduler;
import rx.schedulers.Schedulers;

public class SubscribeOnThreadIO implements SubscribeOnThread {
    @Override
    public Scheduler getScheduler() {
        return Schedulers.io();
    }
}

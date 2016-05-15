package de.bunk.usecase;

import rx.Scheduler;

public interface SubscribeOnThread {
    Scheduler getScheduler();
}

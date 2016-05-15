package de.bunk.usecase;

import com.fernandocejas.arrow.annotations.VisibleForTesting;

import javax.inject.Inject;

import de.bunk.Logger;
import rx.Observable;
import rx.subscriptions.CompositeSubscription;

import static com.fernandocejas.arrow.checks.Preconditions.checkNotNull;

public abstract class UseCase {
    public interface ErrorCallback {
        void onGenericError();
        void onServerError();
    }

    private CompositeSubscription compositeSubscription = new CompositeSubscription();
    private SubscribeOnThread subscribeOnThread;
    private PostExecutionThread postExecutionThread;
    private Logger logger;

    @Inject
    void setPostExecutionThread(PostExecutionThread postExecutionThread) {
        this.postExecutionThread = checkNotNull(postExecutionThread);
    }

    @Inject
    void setSubscribeOnThread(SubscribeOnThread subscribeOnThread) {
        this.subscribeOnThread = checkNotNull(subscribeOnThread);
    }

    @Inject
    void setLogger(Logger logger) {
        this.logger = checkNotNull(logger);
    }

    protected Logger getLogger() {
        return logger;
    }

    protected <T> void subscribe(Observable<T> observable, DefaultSubscriber<T> subscriber) {
        compositeSubscription.add(observable
                .subscribeOn(subscribeOnThread.getScheduler())
                .observeOn(postExecutionThread.getScheduler())
                .subscribe(subscriber));
    }

    public void cancelAll() {
        compositeSubscription.unsubscribe();
    }

    @VisibleForTesting
    void setCompisiteSubscription(CompositeSubscription compositeSubscription) {
        this.compositeSubscription = checkNotNull(compositeSubscription);
    }

}

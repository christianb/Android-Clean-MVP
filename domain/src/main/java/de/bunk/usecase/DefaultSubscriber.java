package de.bunk.usecase;

import de.bunk.Logger;
import retrofit2.adapter.rxjava.HttpException;

import static com.fernandocejas.arrow.checks.Preconditions.checkNotNull;

public class DefaultSubscriber<T> extends rx.Subscriber<T> {
    public static final String TAG = "DefaultSubscriber";

    private UseCase.ErrorCallback errorCallback;
    private Logger logger;

    public DefaultSubscriber(UseCase.ErrorCallback errorCallback, Logger logger) {
        this.errorCallback = checkNotNull(errorCallback);
        this.logger = checkNotNull(logger);
    }

    @Override
    public void onNext(T t) {
        // no-op by default.
    }

    @Override
    public void onCompleted() {
        // no-op by default.
    }

    @Override
    public void onError(Throwable throwable) {
        logger.error(TAG, throwable.getMessage(), throwable);

        Throwable cause = throwable.getCause();

        if (cause != null && cause instanceof HttpException) {
            int code = ((HttpException) cause).code();

            if (code >= 500) {
                errorCallback.onServerError();
                return;
            }

        }

        errorCallback.onGenericError();
    }
}

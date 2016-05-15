package de.bunk.ui;

import com.fernandocejas.arrow.annotations.VisibleForTesting;
import com.fernandocejas.arrow.collections.Lists;

import java.util.List;

import javax.inject.Inject;

import de.bunk.R;
import de.bunk.usecase.UseCase;
import de.bunk.util.StringResource;

public abstract class Presenter<V extends BaseView> implements UseCase.ErrorCallback {

    private StringResource stringResource;

    private final List<UseCase> useCases;

    private V view;

    public Presenter(UseCase... useCases) {
        this.useCases = Lists.newArrayList(useCases);
    }

    public void attachView(V view) {
        this.view = view;
    }

    public void detachView() {
        view = null;
        cancelAllUseCases();
    }

    @Override
    public void onGenericError() {
        if (view != null) {
            view.showError(stringResource.get(R.string.unknown_error));
        }
    }

    @Override
    public void onServerError() {
        if (view != null) {
            view.showError(stringResource.get(R.string.server_error));
        }
    }

    @VisibleForTesting
    void cancelAllUseCases() {
        for (UseCase usecase : useCases) {
            usecase.cancelAll();
        }
    }

    @Inject
    public void setStringResource(StringResource stringResource) {
        this.stringResource = stringResource;
    }

    protected StringResource stringResource() {
        return stringResource;
    }

    public V getView() {
        return view;
    }
}

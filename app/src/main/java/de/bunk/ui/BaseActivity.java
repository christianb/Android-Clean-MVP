package de.bunk.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.bunk.ApplicationComponent;
import de.bunk.BaseApplication;

public abstract class BaseActivity<P extends Presenter> extends Activity implements BaseView {

    @Inject
    P presenter;

    @Nullable
    @Bind(android.R.id.content)
    android.view.View contentView;

    @LayoutRes
    protected abstract int layoutToInflate();

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(layoutToInflate());

        ButterKnife.bind(this);

        doInjection(((BaseApplication) getApplication()).getComponent());
        presenter.attachView(this);
    }


    protected abstract void doInjection(final ApplicationComponent component);

    @Override
    protected void onDestroy() {
        super.onDestroy();

        presenter.detachView();
    }

    @Override
    public void showError(String errorMessage) {
        if (contentView != null) {
            Snackbar.make(contentView, errorMessage, Snackbar.LENGTH_SHORT).show();
        }
    }

    public P getPresenter() {
        return presenter;
    }
}

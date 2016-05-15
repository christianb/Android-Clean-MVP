package de.bunk;

import android.app.Application;

import de.bunk.data.api.ApiModule;

public class BaseApplication extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        initDependencyInjection();

    }

    public ApplicationComponent getComponent() {
        return component;
    }

    private void initDependencyInjection() {
        DaggerApplicationComponent.Builder builder = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .apiModule(new ApiModule());
        component = builder.build();
    }
}

package de.bunk;

import android.content.Context;

import javax.inject.Singleton;

import de.bunk.data.Config;
import de.bunk.util.LoggerProvider;
import de.bunk.usecase.PostExecutionThread;
import de.bunk.usecase.SubscribeOnThread;
import de.bunk.config.BuildConfiguration;
import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private Context context;

    public ApplicationModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context provideContext() {
        return context;
    }

    @Provides
    public SubscribeOnThread provideSubscribeOnThread() {
        return new SubscribeOnThreadIO();
    }

    @Provides
    public PostExecutionThread providePostExecutionThread() {
        return new PostExecutionThreadAndroidMain();
    }

    @Provides
    @Singleton
    public Config provideConfig() {
        return new BuildConfiguration();
    }

    @Provides
    @Singleton
    public Logger provideLogger() {
        return new LoggerProvider();
    }
}

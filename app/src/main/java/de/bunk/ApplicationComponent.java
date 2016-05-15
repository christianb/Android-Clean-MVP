package de.bunk;

import javax.inject.Singleton;

import dagger.Component;
import de.bunk.data.api.ApiModule;
import de.bunk.ui.weather.WeatherActivity;

@Singleton
@Component(modules = {ApplicationModule.class, ApiModule.class})
public interface ApplicationComponent {
    void inject(WeatherActivity weatherActivity);
}

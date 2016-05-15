package de.bunk.ui.weather;

import javax.inject.Inject;

import de.bunk.ui.BaseView;
import de.bunk.ui.Presenter;
import de.bunk.ui.weather.transformer.WeatherTransformer;
import de.bunk.ui.weather.model.Weather;
import de.bunk.usecase.weather.GetWeather;

/**
 * Created by cbunk on 25.01.16.
 */
public class WeatherPresenter extends Presenter<WeatherPresenter.View> implements GetWeather.Callback<Weather> {

    interface View extends BaseView {
        void showWeather(final Weather weather);
    }

    private GetWeather getWeather;
    private WeatherTransformer weatherTransformer;

    @Inject
    public WeatherPresenter(GetWeather getWeather, WeatherTransformer weatherTransformer) {
        super(getWeather);
        this.getWeather = getWeather;
        this.weatherTransformer = weatherTransformer;
    }

    public void getCurrentWeather(final int cityId) {
        getWeather.execute(cityId, weatherTransformer, this);
    }

    @Override
    public void onWeatherLoaded(Weather weather) {
        getView().showWeather(weather);
    }

    @Override
    public void onWeatherError() {
        // no implementation
    }
}

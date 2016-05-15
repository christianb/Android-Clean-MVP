package de.bunk.data.datasource;

import de.bunk.data.api.WeatherService;
import de.bunk.data.entity.weather.WeatherResponseData;
import rx.Observable;

import static com.fernandocejas.arrow.checks.Preconditions.checkNotNull;

public class WeatherApiDataSource implements WeatherDataSource {

    private WeatherService weatherService;

    public WeatherApiDataSource(WeatherService weatherService) {
        this.weatherService = checkNotNull(weatherService);
    }

    @Override
    public Observable<WeatherResponseData> weather(int cityId) {
        return weatherService.weather(cityId);
    }
}

package de.bunk.data.datasource;

import de.bunk.data.entity.weather.WeatherResponseData;
import rx.Observable;

public interface WeatherDataSource {
    Observable<WeatherResponseData> weather(int cityId);
}

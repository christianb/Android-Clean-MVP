package de.bunk.data.api;

import de.bunk.data.entity.weather.WeatherResponseData;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface WeatherService {
    @GET("weather")
    Observable<WeatherResponseData> weather(@Query("id") int cityId);
}

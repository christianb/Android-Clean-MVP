package de.bunk.usecase.weather;

import javax.inject.Inject;

import de.bunk.data.datasource.WeatherDataSource;
import de.bunk.data.entity.weather.WeatherResponseData;
import de.bunk.transformer.AbstractTransformer;
import de.bunk.usecase.DefaultSubscriber;
import de.bunk.usecase.UseCase;
import rx.Observable;
import rx.functions.Func1;

public class GetWeather extends UseCase {

    public interface Callback<Out> extends ErrorCallback {
        void onWeatherLoaded(Out out);
        void onWeatherError();
    }

    private WeatherDataSource weatherDataSource;

    @Inject
    public GetWeather(WeatherDataSource weatherDataSource) {
        this.weatherDataSource = weatherDataSource;
    }

    public <Out> void execute(int cityId, final AbstractTransformer<WeatherResponseData, Out> transformer, final Callback<Out> callback) {
        Observable<Out> observable = weatherDataSource.weather(cityId).map(new Func1<WeatherResponseData, Out>() {
            @Override
            public Out call(WeatherResponseData weatherResponseData) {
                return transformer.transform(weatherResponseData);
            }
        });

        subscribe(observable, new DefaultSubscriber<Out>(callback, getLogger()) {

            @Override
            public void onNext(Out out) {
                super.onNext(out);

                callback.onWeatherLoaded(out);
            }

            @Override
            public void onError(Throwable throwable) {
                super.onError(throwable);

                callback.onWeatherError();
            }
        });
    }
}

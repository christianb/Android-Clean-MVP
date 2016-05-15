package de.bunk.data.api;

import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.bunk.data.entity.AutoValueTypeAdapterFactory;
import de.bunk.data.datasource.WeatherApiDataSource;
import de.bunk.data.datasource.WeatherDataSource;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";

    @Provides
    @Singleton
    public Retrofit provideRetrofit(de.bunk.data.Config config) {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        addLoggingInterceptor(builder, config);
        builder.addInterceptor(new RequestInterceptor());

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder()
                                .registerTypeAdapterFactory(new AutoValueTypeAdapterFactory())
                                .create()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(builder.build()).build();
    }

    private void addLoggingInterceptor(final OkHttpClient.Builder builder, final de.bunk.data.Config config) {
        if (!config.isDebug()) {
            return;
        }

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(httpLoggingInterceptor);
    }

    @Singleton
    @Provides
    public WeatherService provideWeatherService(Retrofit retrofit) {
        return retrofit.create(WeatherService.class);
    }

    @Singleton
    @Provides
    public WeatherDataSource provideWeatherDataSource(WeatherService weatherService) {
        return new WeatherApiDataSource(weatherService);
    }

}

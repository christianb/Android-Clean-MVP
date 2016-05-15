package de.bunk.data.entity;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

import de.bunk.data.entity.weather.MainData;
import de.bunk.data.entity.weather.TempData;
import de.bunk.data.entity.weather.WeatherData;
import de.bunk.data.entity.weather.WeatherResponseData;

public class AutoValueTypeAdapterFactory implements TypeAdapterFactory {

    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        Class<? super T> rawType = type.getRawType();
        if (rawType.equals(WeatherResponseData.class)) {
            return (TypeAdapter<T>) WeatherResponseData.typeAdapter(gson);
        } else if (rawType.equals(WeatherData.class)) {
            return (TypeAdapter<T>) WeatherData.typeAdapter(gson);
        } else if (rawType.equals(MainData.class)) {
            return (TypeAdapter<T>) MainData.typeAdapter(gson);
        } else if (rawType.equals(TempData.class)) {
            return (TypeAdapter<T>) TempData.typeAdapter(gson);
        }

        return null;
    }
}

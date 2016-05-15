package de.bunk.ui.weather.transformer;

import org.jetbrains.annotations.Nullable;

import javax.inject.Inject;

import de.bunk.data.entity.weather.WeatherResponseData;
import de.bunk.transformer.AbstractTransformer;
import de.bunk.ui.weather.model.Weather;

public class WeatherTransformer extends AbstractTransformer<WeatherResponseData, Weather> {

    @Inject
    public WeatherTransformer() {}

    @Nullable
    @Override
    public Weather transform(@Nullable WeatherResponseData weatherResponseData) {
        if (weatherResponseData == null) {
            return null;
        }

        return Weather.create(weatherResponseData.city(), weatherResponseData.main().temp(),
                weatherResponseData.weather().get(0).description());
    }
}

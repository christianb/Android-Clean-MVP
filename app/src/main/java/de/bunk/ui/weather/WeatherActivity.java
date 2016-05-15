package de.bunk.ui.weather;

import android.support.annotation.NonNull;
import android.widget.TextView;

import java.text.MessageFormat;

import butterknife.Bind;
import de.bunk.ApplicationComponent;
import de.bunk.R;
import de.bunk.ui.BaseActivity;
import de.bunk.ui.weather.model.Weather;

public class WeatherActivity extends BaseActivity<WeatherPresenter> implements WeatherPresenter.View {

    private static final int BERLIN_CITY_ID = 2950159;

    @Bind(R.id.retrofit_activity_city_textview)
    TextView cityTextView;

    @Bind(R.id.retrofit_activity_description_textview)
    TextView descriptionTextView;

    @Bind(R.id.retrofit_activity_temperature_textview)
    TextView temperatureTextView;

    @Override
    protected int layoutToInflate() {
        return R.layout.weather_activity;
    }

    @Override
    protected void doInjection(ApplicationComponent component) {
        component.inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        getPresenter().getCurrentWeather(BERLIN_CITY_ID);
    }

    @NonNull
    private String addDegreeSymbol(final String currentTemp) {
        return MessageFormat.format("{0}°", currentTemp);
    }

    @Override
    public void showWeather(Weather weather) {
        cityTextView.setText(weather.city());
        descriptionTextView.setText(weather.description());
        temperatureTextView.setText(addDegreeSymbol(weather.temp(Weather.Unit.CELSIUS)));
    }
}

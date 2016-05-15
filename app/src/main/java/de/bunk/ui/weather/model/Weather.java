package de.bunk.ui.weather.model;

import android.support.annotation.IntDef;

import com.google.auto.value.AutoValue;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import de.bunk.ui.ViewModel;

@AutoValue
public abstract class Weather extends ViewModel {

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({
            Unit.CELSIUS,
            Unit.FAHRENHEIT,
            Unit.KELVIN
    })

    public @interface Unit {
        int CELSIUS = 0;
        int FAHRENHEIT = 1;
        int KELVIN = 2;
    }

    public abstract String city();

    /**
     * Return the temperature in Kelvin
     *
     * @return temperature in Kelvin
     */
    public abstract Double temp();

    public abstract String description();

    public static Weather create(String city, Double temp, String description) {
        return new AutoValue_Weather(city, temp, description);
    }

    public String temp(@Unit int unit) {
        switch (unit) {
            case Unit.CELSIUS:
                return ((Long) Math.round(temp() - 273.15d)).toString();

            case Unit.FAHRENHEIT:
                return ((Long) Math.round((temp() - 273.15d) * 1.8000d + 32)).toString();

            default:
                return temp().toString();
        }
    }
}

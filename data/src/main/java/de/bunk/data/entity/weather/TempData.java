
package de.bunk.data.entity.weather;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import org.jetbrains.annotations.Nullable;

@AutoValue
public abstract class TempData {

    public enum Unit {
        CELSIUS,
        FAHRENHEIT,
        KELVIN;
    }

    public abstract Double day();
    public abstract Double min();
    public abstract Double max();
    public abstract Double night();
    public abstract Double eve();
    public abstract Double morn();

    public static TypeAdapter<TempData> typeAdapter(Gson gson) {
        return new AutoValue_TempData.GsonTypeAdapter(gson);
    }

    @Nullable
    public static Double convert(@Nullable final Double temp, Unit unit) {
        if (temp == null) {
            return null;
        }

        switch (unit) {

            case CELSIUS :
                return temp - 273.15;

            case FAHRENHEIT :
                return (temp - 273.15) * 1.8000 + 32;

            default :
                return temp;
        }
    }

    public Double getDay(Unit unit) {
        return convert(day(), unit);
    }

    public Double getMin(Unit unit) {
        return convert(min(), unit);
    }

    public Double getMax(Unit unit) {
        return convert(max(), unit);
    }

    public Double getNight(Unit unit) {
        return convert(night(), unit);
    }

    public Double getEve(Unit unit) {
        return convert(eve(), unit);
    }

    public Double getMorn(Unit unit) {
        return convert(morn(), unit);
    }

}

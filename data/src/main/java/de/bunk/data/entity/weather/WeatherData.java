
package de.bunk.data.entity.weather;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

@AutoValue
public abstract class WeatherData {

    public abstract String description();

    public static TypeAdapter<WeatherData> typeAdapter(Gson gson) {
        return new AutoValue_WeatherData.GsonTypeAdapter(gson);
    }
}

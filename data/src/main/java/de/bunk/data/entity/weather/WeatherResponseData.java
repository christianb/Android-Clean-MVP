
package de.bunk.data.entity.weather;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@AutoValue
public abstract class WeatherResponseData {

    public abstract List<WeatherData> weather();
    public abstract MainData main();

    @SerializedName("name")
    public abstract String city();

    public static TypeAdapter<WeatherResponseData> typeAdapter(Gson gson) {
        return new AutoValue_WeatherResponseData.GsonTypeAdapter(gson);
    }

}

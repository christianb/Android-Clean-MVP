
package de.bunk.data.entity.weather;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

@AutoValue
public abstract class MainData {

    public abstract Double temp();

    public static TypeAdapter<MainData> typeAdapter(Gson gson) {
        return new AutoValue_MainData.GsonTypeAdapter(gson);
    }

    public Double temp(TempData.Unit unit) {
        return TempData.convert(temp(), unit);
    }
}

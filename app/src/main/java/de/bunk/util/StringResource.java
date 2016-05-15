package de.bunk.util;

import android.content.Context;
import android.support.annotation.StringRes;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class StringResource implements Resource<String> {
    private Context context;

    @Inject
    public StringResource(Context context) {
        this.context = context.getApplicationContext();
    }

    @Override
    public String get(@StringRes int resId) {
        return context.getString(resId);
    }
}

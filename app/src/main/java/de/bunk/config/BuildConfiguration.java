package de.bunk.config;

import de.bunk.BuildConfig;
import de.bunk.data.Config;

public class BuildConfiguration implements Config {
    @Override
    public boolean isDebug() {
        return BuildConfig.DEBUG;
    }
}

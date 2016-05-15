package de.bunk.util;

import android.util.Log;

import de.bunk.Logger;

public class LoggerProvider implements Logger {
    @Override
    public void debug(String tag, String message) {
        Log.d(tag, message);
    }

    @Override
    public void error(String tag, String message) {
        Log.e(tag, message);
    }

    @Override
    public void error(String tag, String message, Throwable throwable) {
        Log.e(tag, message, throwable);
    }
}

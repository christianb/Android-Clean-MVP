package de.bunk;

public interface Logger {

    void debug(String tag, String message);
    void error(String tag, String message);
    void error(String tag, String message, Throwable throwable);

}

package org.trinition.logging.android;

import androidx.annotation.NonNull;

import org.trinition.logging.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AndroidLoggerFactory implements LoggerFactory<AndroidLogger> {

    private static final Map<String, AndroidLogger> loggersByName = new ConcurrentHashMap<>();

    @NonNull
    @Override
    public AndroidLogger create(@NonNull String name) {
        return loggersByName.computeIfAbsent(name, AndroidLogger::new);
    }
}

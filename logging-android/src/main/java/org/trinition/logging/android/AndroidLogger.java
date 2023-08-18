package org.trinition.logging.android;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.trinition.logging.Logger;

import java.util.Map;
import java.util.function.Supplier;

/**
 * {@link Logger} implementation that logs to Android's {@link Log}, with any optional data maps
 * inlined into the message.
 */
public class AndroidLogger implements Logger {

    @NonNull
    private final String tag;

    @NonNull static String toString(@NonNull String message, @NonNull String key1, @Nullable  Object value1) {
        return  new StringBuilder(message)
            .append("; ").append(key1).append('=').append(value1)
            .toString();
    }

    @NonNull static String toString(@NonNull String message, @NonNull String key1, @Nullable  Object value1, @NonNull String key2, @Nullable  Object value2) {
        return  new StringBuilder(message)
            .append("; ").append(key1).append('=').append(value1)
            .append("; ").append(key2).append('=').append(value2)
            .toString();
    }

    @NonNull static String toString(@NonNull String message, @NonNull String key1, @Nullable  Object value1, @NonNull String key2, @Nullable  Object value2, @NonNull String key3, @Nullable  Object value3) {
        return  new StringBuilder(message)
            .append("; ").append(key1).append('=').append(value1)
            .append("; ").append(key2).append('=').append(value2)
            .append("; ").append(key3).append('=').append(value3)
            .toString();
    }

    @NonNull static String toString(@NonNull String message, @NonNull Supplier<Map<String, Object>> dataSupplier) {
        return toString(message, dataSupplier.get());
    }

    @NonNull static String toString(@NonNull String message, @Nullable Map<String, Object> data) {
        StringBuilder string = new StringBuilder(message);
        if(data != null) {
            for(Map.Entry<String,Object> entry : data.entrySet()) {
                string.append("; ").append(entry.getKey()).append('=').append(entry.getValue());
            }
        }
        return string.toString();
    }

   AndroidLogger(@NonNull String tag) {
        this.tag = tag;
    }

    private static Integer overrideLevel = Log.VERBOSE;

    private static boolean isLoggable(String tag, int level) {
        if(overrideLevel != null) {
            return (level >= overrideLevel.intValue());
        }
        return Log.isLoggable(tag, level);
    }

    @Override
    public void v(String message) {
        if (!isLoggable(tag, Log.VERBOSE)) return;
        Log.v(tag, message);
    }

    @Override
    public void v(@NonNull String message, @NonNull String key1, @Nullable Object val1) {
        if (!isLoggable(tag, Log.VERBOSE)) return;
        v(toString(message, key1, val1));
    }

    @Override
    public void v(@NonNull String message, @NonNull String key1, @Nullable Object val1, String key2, Object val2) {
        if (!isLoggable(tag, Log.VERBOSE)) return;
        v(toString(message, key1, val1, key2, val2));
    }

    @Override
    public void v(@NonNull String message, @NonNull String key1, @Nullable Object val1, String key2, Object val2, String key3, Object val3) {
        if (!isLoggable(tag, Log.VERBOSE)) return;
        v(toString(message, key1, val1, key2, val2, key3, val3));
    }

    @Override
    public void v(@NonNull String message, @NonNull Supplier<Map<String, Object>> valueSupplier) {
        if (!isLoggable(tag, Log.VERBOSE)) return;
        v(toString(message, valueSupplier));
    }

    @Override
    public void v(@NonNull String message, @NonNull Map<String, Object> data) {
        if (!isLoggable(tag, Log.VERBOSE)) return;
        v(toString(message, data));
    }

    @Override
    public void d(String message) {
        Log.d(tag, message);
    }

    @Override
    public void d(String message, String key1, Object val1) {
        if (isLoggable(tag, Log.DEBUG)) d(toString(message, key1, val1));
    }

    @Override
    public void d(String message, String key1, Object val1, String key2, Object val2) {
        if (isLoggable(tag, Log.DEBUG)) d(toString(message, key1, val1, key2, val2));
    }

    @Override
    public void d(String message, String key1, Object val1, String key2, Object val2, String key3, Object val3) {
        if (isLoggable(tag, Log.DEBUG)) d(toString(message, key1, val1, key2, val2, key3, val3));
    }

    @Override
    public void d(String message, Supplier<Map<String, Object>> valueSupplier) {
        if (isLoggable(tag, Log.DEBUG)) d(toString(message, valueSupplier));
    }

    @Override
    public void d(String message, Map<String, Object> data) {
        if (isLoggable(tag, Log.DEBUG)) d(toString(message, data));
    }

    @Override
    public void i(String message) {
        Log.i(tag, message);
    }

    @Override
    public void i(String message, String key1, Object val1) {
        if (isLoggable(tag, Log.INFO)) i(toString(message, key1, val1));
    }

    @Override
    public void i(String message, String key1, Object val1, String key2, Object val2) {
        if (isLoggable(tag, Log.INFO)) i(toString(message, key1, val1, key2, val2));
    }

    @Override
    public void i(String message, String key1, Object val1, String key2, Object val2, String key3, Object val3) {
        if (isLoggable(tag, Log.INFO)) i(toString(message, key1, val1, key2, val2, key3, val3));
    }

    @Override
    public void i(String message, Supplier<Map<String, Object>> valueSupplier) {
        if (isLoggable(tag, Log.INFO)) i(toString(message, valueSupplier));
    }

    @Override
    public void i(String message, Map<String, Object> data) {
        if (isLoggable(tag, Log.INFO)) i(toString(message, data));
    }

    @Override
    public void w(String message) {
        Log.w(tag, message);
    }

    @Override
    public void w(String message, String key1, Object val1) {
        if (isLoggable(tag, Log.WARN)) w(toString(message, key1, val1));
    }

    @Override
    public void w(String message, String key1, Object val1, String key2, Object val2) {
        if (isLoggable(tag, Log.WARN)) w(toString(message, key1, val1, key2, val2));
    }

    @Override
    public void w(String message, String key1, Object val1, String key2, Object val2, String key3, Object val3) {
        if (isLoggable(tag, Log.WARN)) w(toString(message, key1, val1, key2, val2, key3, val3));
    }

    @Override
    public void w(String message, Supplier<Map<String, Object>> valueSupplier) {
        if (isLoggable(tag, Log.WARN)) w(toString(message, valueSupplier));
    }

    @Override
    public void w(String message, Map<String, Object> data) {
        if (isLoggable(tag, Log.WARN)) w(toString(message, data));
    }


    @Override
    public void e(String message) {
        Log.e(tag, message);
    }

    @Override
    public void e(String message, Throwable cause) {
        Log.e(tag, message, cause);
    }

    @Override
    public void e(String message, String key1, Object val1) {
        if (isLoggable(tag, Log.ERROR)) Log.e(tag, toString(message, key1, val1));
    }

    @Override
    public void e(String message, String key1, Object val1, Throwable cause) {
        if (isLoggable(tag, Log.ERROR)) Log.e(tag, toString(message, key1, val1), cause);
    }

    @Override
    public void e(String message, String key1, Object val1, String key2, Object val2) {
        if (isLoggable(tag, Log.ERROR)) Log.e(tag, toString(message, key1, val1, key2, val2));
    }

    @Override
    public void e(String message, String key1, Object val1, String key2, Object val2, Throwable cause) {
        if (isLoggable(tag, Log.ERROR)) Log.e(tag, toString(message, key1, val1, key2, val2), cause);
    }

    @Override
    public void e(String message, String key1, Object val1, String key2, Object val2, String key3, Object val3) {
        if (isLoggable(tag, Log.ERROR)) Log.e(tag, toString(message, key1, val1, key2, val2, key3, val3));
    }

    @Override
    public void e(String message, String key1, Object val1, String key2, Object val2, String key3, Object val3, Throwable cause) {
        if (isLoggable(tag, Log.ERROR)) Log.e(tag, toString(message, key1, val1, key2, val2, key3, val3), cause);
    }

    @Override
    public void e(String message, Supplier<Map<String, Object>> valueSupplier) {
        if (isLoggable(tag, Log.ERROR)) Log.e(tag, toString(message, valueSupplier));
    }

    @Override
    public void e(String message, Supplier<Map<String, Object>> valueSupplier, Throwable cause) {
        if (isLoggable(tag, Log.ERROR)) Log.e(tag, toString(message, valueSupplier), cause);
    }

    @Override
    public void e(String message, Map<String, Object> data) {
        if (isLoggable(tag, Log.ERROR)) Log.e(tag, toString(message, data));
    }

    @Override
    public void e(String message, Map<String, Object> data, Throwable cause) {
        if (isLoggable(tag, Log.ERROR)) Log.e(tag, toString(message, data), cause);
    }


}

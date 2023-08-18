package org.trinition.logging.android;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mockStatic;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentMatchers;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.trinition.logging.android.AndroidLogger;
import org.trinition.test.AutoCloseFieldsExtension;

import java.util.stream.Stream;

@ExtendWith({MockitoExtension.class, AutoCloseFieldsExtension.class})
public class AndroidLoggerTest {

    private String tag = "testTag";
    private MockedStatic<Log> logMock;

    @BeforeEach
    public void before() {
        tag = "testTag";
        logMock = mockStatic(Log.class);
        theLogger = null;
    }

    @AfterEach
    public void after() {
        logMock.close();
        logMock = null;
    }

    private AndroidLogger theLogger = null;
    @NonNull private AndroidLogger getLogger() {
        if(theLogger == null) theLogger = new AndroidLogger(tag);
        return theLogger;
    }

    private void logMockIsLoggable(int level, boolean isLoggable) {
        logMock.when(() -> Log.isLoggable(eq(tag), eq(level))).thenReturn(isLoggable);
        if(isLoggable) {
            switch(level) {
                case Log.VERBOSE:
                    logMock.when(() -> Log.v(eq(tag), any(String.class))).thenReturn(1);
                    logMock.when(() -> Log.v(eq(tag), any(String.class), any(Throwable.class))).thenReturn(1);
                    break;
                case Log.DEBUG:
                    logMock.when(() -> Log.d(eq(tag), any(String.class))).thenReturn(1);
                    logMock.when(() -> Log.d(eq(tag), any(String.class), any(Throwable.class))).thenReturn(1);
                    break;
                case Log.INFO:
                    logMock.when(() -> Log.i(eq(tag), any(String.class))).thenReturn(1);
                    logMock.when(() -> Log.i(eq(tag), any(String.class), any(Throwable.class))).thenReturn(1);
                    break;
                case Log.WARN:
                    logMock.when(() -> Log.w(eq(tag), any(String.class))).thenReturn(1);
                    logMock.when(() -> Log.w(eq(tag), any(String.class), any(Throwable.class))).thenReturn(1);
                    break;
                case Log.ERROR:
                    logMock.when(() -> Log.e(eq(tag), any(String.class))).thenReturn(1);
                    logMock.when(() -> Log.e(eq(tag), any(String.class), any(Throwable.class))).thenReturn(1);
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported level; level="+level);
            }
        }
    }

    private void logMockVerifyNotLogged(int level) {
        // DISABLED: because we have AndroidLogger.overrideLevel hard-coded to non-null, which bypasses Log.IsLoggable
        // logMock.verify(() -> Log.isLoggable(tag, level), times(1));
        switch(level) {
            case Log.VERBOSE:
                logMock.verifyNoMoreInteractions();
                break;
            case Log.DEBUG:
                logMock.verifyNoMoreInteractions();
                break;
            case Log.INFO:
                logMock.verifyNoMoreInteractions();
                break;
            case Log.WARN:
                logMock.verifyNoMoreInteractions();
                break;
            case Log.ERROR:
                logMock.verifyNoMoreInteractions();
                break;
            default:
                throw new IllegalArgumentException("Unsupported level; level=" + level);
        }
    }

    private void logMockVerifyLogged(int level, @NonNull String message) {
        // DISABLED: because we have AndroidLogger.overrideLevel hard-coded to non-null, which bypasses Log.IsLoggable
        // logMock.verify(() -> Log.isLoggable(tag, level), times(1));
        switch(level) {
            case Log.VERBOSE:
                logMock.verify(() -> Log.v(tag, message), Mockito.times(1));
                break;
            case Log.DEBUG:
                logMock.verify(() -> Log.d(tag, message), Mockito.times(1));
                break;
            case Log.INFO:
                logMock.verify(() -> Log.i(tag, message), Mockito.times(1));
                break;
            case Log.WARN:
                logMock.verify(() -> Log.w(tag, message), Mockito.times(1));
                break;
            case Log.ERROR:
                logMock.verify(() -> Log.e(tag, message), Mockito.times(1));
                break;
            default:
                throw new IllegalArgumentException("Unsupported level; level=" + level);
        }
    }

    private void logMockVerifyLogged(int level, @NonNull String message, @NonNull Throwable cause) {
        logMock.verify(() -> Log.isLoggable(tag, level), Mockito.times(1));
        switch(level) {
            case Log.VERBOSE:
                logMock.verify(() -> Log.v(tag, message, cause), Mockito.times(1));
                break;
            case Log.DEBUG:
                logMock.verify(() -> Log.d(tag, message, cause), Mockito.times(1));
                break;
            case Log.INFO:
                logMock.verify(() -> Log.i(tag, message, cause), Mockito.times(1));
                break;
            case Log.WARN:
                logMock.verify(() -> Log.w(tag, message, cause), Mockito.times(1));
                break;
            case Log.ERROR:
                logMock.verify(() -> Log.e(tag, message, cause), Mockito.times(1));
                break;
            default:
                throw new IllegalArgumentException("Unsupported level; level=" + level);
        }
    }

    private static Stream<Arguments> verify() {
        return Stream.of(
            Arguments.of(Log.VERBOSE, true,  "testMessage", "testKey", "testValue"),
            Arguments.of(Log.VERBOSE, true,  "testMessage", "testKey", null),
//            Arguments.of(Log.VERBOSE, false, "testMessage", "testKey", "testValue"),
            Arguments.of(Log.DEBUG,   true,  "testMessage", "testKey", "testValue"),
            Arguments.of(Log.DEBUG,   true,  "testMessage", "testKey", null),
//            Arguments.of(Log.DEBUG,   false, "testMessage", "testKey", "testValue"),
            Arguments.of(Log.INFO,    true,  "testMessage", "testKey", "testValue"),
            Arguments.of(Log.INFO,    true,  "testMessage", "testKey", null),
//            Arguments.of(Log.INFO,    false, "testMessage", "testKey", "testValue"),
            Arguments.of(Log.WARN,    true,  "testMessage", "testKey", "testValue"),
            Arguments.of(Log.WARN,    true,  "testMessage", "testKey", null),
//            Arguments.of(Log.WARN,    false, "testMessage", "testKey", "testValue"),
            Arguments.of(Log.ERROR,   true,  "testMessage", "testKey", "testValue"),
            Arguments.of(Log.ERROR,   true,  "testMessage", "testKey", null)
//            Arguments.of(Log.ERROR,   false, "testMessage", "testKey", "testValue")
        );
    }

    @ParameterizedTest @MethodSource
    public void verify(int level, boolean isLoggable, @NonNull  String message, @NonNull String key1, @Nullable Object val1) {
        // Given
        // DISABLED: because we have AndroidLogger.overrideLevel hard-coded to non-null, which bypasses Log.IsLoggable
        //logMockIsLoggable(level, isLoggable);

        // When
        switch(level) {
            case Log.VERBOSE:
                getLogger().v(message, key1, val1);
                break;
            case Log.DEBUG:
                getLogger().d(message, key1, val1);
                break;
            case Log.INFO:
                getLogger().i(message, key1, val1);
                break;
            case Log.WARN:
                getLogger().w(message, key1, val1);
                break;
            case Log.ERROR:
                getLogger().e(message, key1, val1);
                break;
            default:
                throw new IllegalArgumentException("Unsupported level; level=" + level);
        }

        // Then
        if(isLoggable) {
            logMockVerifyLogged(level, AndroidLogger.toString(message, key1, val1));
        }
        else {
            logMockVerifyNotLogged(level);
        }
    }

}
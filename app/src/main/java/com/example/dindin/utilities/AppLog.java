package com.example.dindin.utilities;

/**
 * Created by Kevin on 11/19/2016.
 * Used to Log Information when in Debug Mode.
 */

public class AppLog {

    public static final boolean isDebug = false;

    public static final void Log(String tag, String message) {
        if (isDebug) {
            android.util.Log.i(tag, message);
        }
    }

    public static final void handleException(String tag, Exception e) {
        if (isDebug) {
            if (e != null) {
                android.util.Log.d(tag, e.getMessage() + "");
                e.printStackTrace();
            }
        }
    }

}
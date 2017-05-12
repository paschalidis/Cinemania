package com.example.android.popularmovies.utilities;

import android.util.Log;

import java.io.IOException;

/**
 * Network Utilities Class used to build the URL and get
 * the response from URL.
 *
 * @author Pavlos Paschalidis
 */
public class NetworkUtilities {

    private static final String TAG = NetworkUtilities.class.getSimpleName();
    private static final String PING_COMMAND = "/system/bin/ping -c 1 8.8.8.8";

    /**
     * This method ping Google Public DNS (8.8.8.8) to check internet connection
     *
     * @return boolean true if device is connected to internet
     */
    public static boolean hasInternetConnection() {
        boolean hasInternet = false;
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec(PING_COMMAND);
            int exitValue = ipProcess.waitFor();
            hasInternet = (exitValue == 0);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        Log.d(TAG, "Internet Connection: " + hasInternet);

        return hasInternet;
    }

}

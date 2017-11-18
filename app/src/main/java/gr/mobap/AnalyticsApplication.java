package gr.mobap;

import android.app.Application;
import android.os.Environment;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.onesignal.OneSignal;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;

import java.io.File;

/**
 * This is a subclass of {@link Application} used to provide shared objects for this app, such as
 * the {@link Tracker}.
 */
public class AnalyticsApplication extends Application {
    private Tracker mTracker;
    protected File extStorageAppBasePath;
    protected File extStorageAppCachePath;

    @Override
    public void onCreate() {
        super.onCreate();
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
        Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
                .applicationId("JQLmtUO2gtFqtsTqFg6otK8KCZ0lXE") //TODO αλλαγή κωδικών
                .clientKey(null).server("http://hellenicparliament.herokuapp.com/parse/") // The trailing slash is important.
                .build()
        );

        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();

        // If you would like all objects to be private by default, remove this
        // line.
        defaultACL.setPublicReadAccess(true);

        ParseACL.setDefaultACL(defaultACL, true);


        // Check if the external storage is writeable
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            // Retrieve the base path for the application in the external storage
            File externalStorageDir = Environment.getExternalStorageDirectory();
            if (externalStorageDir != null) {
                // {SD_PATH}/Android/data/gr.mobap
                extStorageAppBasePath = new File(externalStorageDir.getAbsolutePath() +
                        File.separator + "Android" + File.separator + "data" +
                        File.separator + getPackageName());
            }
            if (extStorageAppBasePath != null) {
                // {SD_PATH}/Android/data/gr.mobap/cache
                extStorageAppCachePath = new File(extStorageAppBasePath.getAbsolutePath() +
                        File.separator + "cache");
                boolean isCachePathAvailable = true;
                if (!extStorageAppCachePath.exists()) {
                    // Create the cache path on the external storage
                    isCachePathAvailable = extStorageAppCachePath.mkdirs();
                }
                if (!isCachePathAvailable) {
                    // Unable to create the cache path
                    extStorageAppCachePath = null;
                }
            }
        }
    }

    @Override
    public File getCacheDir() {
        // NOTE: this method is used in Android 2.2 and higher
        if (extStorageAppCachePath != null) {
            // Use the external storage for the cache
            return extStorageAppCachePath;
        } else {
            // /data/data/com.devahead.androidwebviewcacheonsd/cache
            return super.getCacheDir();
        }
    }


    /**
     * Gets the default {@link Tracker} for this {@link Application}.
     *
     * @return tracker
     */
    synchronized public Tracker getDefaultTracker() {
        if (mTracker == null) {
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            // To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
            mTracker = analytics.newTracker(R.xml.app_tracker);
        }
        return mTracker;
    }
}


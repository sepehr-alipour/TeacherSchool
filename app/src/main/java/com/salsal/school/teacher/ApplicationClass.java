package com.salsal.school.teacher;

import android.app.Application;
import android.content.res.Configuration;

import java.util.Locale;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Sepehr on 12/4/2017.
 */

public class ApplicationClass extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Configuration configuration = getResources().getConfiguration();
        configuration.setLayoutDirection(new Locale("fa"));
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("font/iransans.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());
    }
}

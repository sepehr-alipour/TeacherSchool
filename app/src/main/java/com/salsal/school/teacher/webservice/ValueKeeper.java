package com.salsal.school.teacher.webservice;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Taraabar on 12/28/2016.
 */
public class ValueKeeper {
    private static ValueKeeper ourInstance = new ValueKeeper();
    public static final String BASE_URL = "http://192.168.2.119:80";
    //public static final String BASE_URL = "http://192.168.1.12:80";
    //public static final String BASE_URL = "http://192.168.43.114:80";
    public static final int PER_PAGE = 10;
    private static final String PRE_USER_PROFILE = "user_profile";
    public static final String PREF_TOKEN = "token";
    public static final String PREF_ID = "id";

    public static ValueKeeper getInstance() {
        return ourInstance;
    }

    private ValueKeeper() {
    }


    public static void SaveUserProfile(Context context, String id, String token) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PRE_USER_PROFILE, MODE_PRIVATE).edit();
        editor.putString(PREF_TOKEN, token);
        editor.putString(PREF_ID, id);
        editor.apply();
    }

    public static HashMap<String, String> getUserProfile(Context context) {
        HashMap<String, String> profile = new HashMap<>();
        SharedPreferences prefs = context.getSharedPreferences(PRE_USER_PROFILE, MODE_PRIVATE);
        profile.put(PREF_ID, prefs.getString(PREF_ID, ""));
        profile.put(PREF_TOKEN, prefs.getString(PREF_TOKEN, ""));
        return profile;
    }

    public static void removeAllPrefrences(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PRE_USER_PROFILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
    }


}

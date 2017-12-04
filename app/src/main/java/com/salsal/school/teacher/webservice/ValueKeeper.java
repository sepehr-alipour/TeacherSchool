package com.salsal.school.teacher.webservice;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Taraabar on 12/28/2016.
 */
public class ValueKeeper {
    private static ValueKeeper ourInstance = new ValueKeeper();
    public static final String BASE_URL = "";
    public static final int PER_PAGE = 10;

    public static ValueKeeper getInstance() {
        return ourInstance;
    }

    private ValueKeeper() {
    }


    public static void saveToken(Context context, String token) {
        SharedPreferences.Editor editor = context.getSharedPreferences("userProfile", MODE_PRIVATE).edit();
        // editor.putString("username", username);
        //  editor.putString("password", password);
        editor.putString("token", token);
        editor.commit();
    }

    public static String getToken(Context context) {
        // HashMap hashMap = null;
        SharedPreferences prefs = context.getSharedPreferences("userProfile", MODE_PRIVATE);
        if (prefs.contains("token"))
        {
            //  hashMap = new HashMap();
            // hashMap.put("username", prefs.getString("username", ""));
            // hashMap.put("password", prefs.getString("password", ""));
            // hashMap.put("token", prefs.getString("token", ""));
            return prefs.getString("token", "");
        }
        return null;
    }

    public static void removeAllPrefrences(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("userProfile", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
    }


}

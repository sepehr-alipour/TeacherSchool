package com.salsal.school.teacher.view;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.salsal.school.teacher.utils.LocaleManager;


public class BaseActivity extends AppCompatActivity {
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleManager.setLocale(newBase));

        //CalligraphyContextWrapper.wrap(context)
    }

}

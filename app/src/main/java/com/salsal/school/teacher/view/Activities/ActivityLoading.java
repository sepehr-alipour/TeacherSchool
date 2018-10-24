package com.salsal.school.teacher.view.Activities;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.salsal.school.teacher.R;
import com.salsal.school.teacher.view.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityLoading extends BaseActivity {

    @BindView (R.id.imgLogo)
    ImageView imgLogo;
    public static ActivityLoading instance = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        ButterKnife.bind(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        TranslateAnimation mAnimation = new TranslateAnimation(
                TranslateAnimation.ABSOLUTE, 0f,
                TranslateAnimation.ABSOLUTE, 0f,
                TranslateAnimation.RELATIVE_TO_PARENT, 0f,
                TranslateAnimation.RELATIVE_TO_PARENT, 0.05f);
        mAnimation.setDuration(700);
        mAnimation.setRepeatCount(-1);
        mAnimation.setRepeatMode(Animation.REVERSE);
        mAnimation.setInterpolator(new LinearInterpolator());
        imgLogo.setAnimation(mAnimation);


        instance = this;
    }

    @Override
    public void finish() {
        instance = null;
        super.finish();
    }
}

package com.salsal.school.teacher.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.ImageView;

import com.salsal.school.teacher.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Sepehr on 12/4/2017.
 */

public class ActivityLogin extends AppCompatActivity {
    @BindView (R.id.imageView)
    ImageView imageView;
    @BindView (R.id.edtUsername)
    AppCompatEditText edtUsername;
    @BindView (R.id.edtPassword)
    AppCompatEditText edtPassword;
    @BindView (R.id.btnLogin)
    AppCompatButton btnLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityLogin.this, ActivityMain.class));
                finish();
            }
        });

    }
}

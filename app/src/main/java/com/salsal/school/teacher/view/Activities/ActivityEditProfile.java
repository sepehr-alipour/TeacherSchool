package com.salsal.school.teacher.view.Activities;

import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.salsal.school.teacher.R;
import com.salsal.school.teacher.interfaces.APIErrorResult;
import com.salsal.school.teacher.interfaces.ApiInterface;
import com.salsal.school.teacher.interfaces.CallbackHandler;
import com.salsal.school.teacher.model.TeacherProfileReq;
import com.salsal.school.teacher.model.TeacherProfileRes;
import com.salsal.school.teacher.utils.PreferenceManager;
import com.salsal.school.teacher.view.BaseActivity;
import com.salsal.school.teacher.webservice.WebServiceHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.hamsaa.persiandatepicker.Listener;
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog;
import ir.hamsaa.persiandatepicker.util.PersianCalendar;
import retrofit2.Response;

public class ActivityEditProfile extends BaseActivity implements View.OnClickListener, Listener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edtEducation)
    AppCompatEditText edtEducation;
    @BindView(R.id.edtBirthday)
    AppCompatEditText edtBirthday;
    @BindView(R.id.edtEmail)
    AppCompatEditText edtEmail;
    @BindView(R.id.edtPhone)
    AppCompatEditText edtPhone;
    @BindView(R.id.btnSend)
    AppCompatButton btnSend;
    @BindView(R.id.list)
    ScrollView list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);
        ButterKnife.bind(this);
        toolbar.setTitle("ویرایش مشخصات");
        btnSend.setOnClickListener(this);
        edtBirthday.setOnClickListener(this);
        WebServiceHelper.get(this).getTeacherProfile(PreferenceManager.getUserProfile(this).get(PreferenceManager.PREF_ID), PreferenceManager.getUserProfile(this).get(PreferenceManager.PREF_TOKEN))
                .enqueue(new CallbackHandler<TeacherProfileRes>(this, true, true) {
                    @Override
                    public void onSuccess(Response<TeacherProfileRes> response) {
                        edtBirthday.setText(response.body().getData().getBirthDate());
                        edtEducation.setText(response.body().getData().getEducation());
                        edtEmail.setText(response.body().getData().getEmail());
                        edtPhone.setText(response.body().getData().getPhoneNumber());
                    }

                    @Override
                    public void onFailed(APIErrorResult errorResult) {

                    }
                });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSend:
                TeacherProfileReq teacherProfileReq = new TeacherProfileReq();
                teacherProfileReq.setBirthDate(edtBirthday.getText().toString());
                teacherProfileReq.setEmail(edtEmail.getText().toString());
                teacherProfileReq.setPhoneNumber(edtPhone.getText().toString());

                WebServiceHelper.get(ActivityEditProfile.this).updateProfile(PreferenceManager.getUserProfile(ActivityEditProfile.this).get(PreferenceManager.PREF_ID),
                        PreferenceManager.getUserProfile(ActivityEditProfile.this).get(PreferenceManager.PREF_TOKEN)
                        , teacherProfileReq).enqueue(new CallbackHandler<JsonObject>(ActivityEditProfile.this, true, true) {
                    @Override
                    public void onSuccess(Response<JsonObject> response) {

                        Toast.makeText(ActivityEditProfile.this, "تغییرات با موفیت اعمال شد", Toast.LENGTH_SHORT).show();
                        ActivityEditProfile.this.finish();

                    }

                    @Override
                    public void onFailed(APIErrorResult errorResult) {

                    }
                });
                break;
            case R.id.edtBirthday:
                new PersianDatePickerDialog(this)
                        .setPositiveButtonString("تایید")
                        .setNegativeButton("انصراف")
                        .setActionTextColor(getResources().getColor(R.color.colorAccent))
                        .setTodayButtonVisible(false)
                        .setMaxYear(PersianDatePickerDialog.THIS_YEAR)
                        .setMinYear(1300)
                        .setActionTextColor(Color.GRAY)
                        .setListener(this).show();
                break;
        }
    }

    @Override
    public void onDateSelected(PersianCalendar persianCalendar) {
        edtBirthday.setText(persianCalendar.getPersianShortDate());
    }

    @Override
    public void onDismissed() {

    }
}

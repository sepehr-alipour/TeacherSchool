package com.salsal.school.teacher.view.Activities;

import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.salsal.school.teacher.R;
import com.salsal.school.teacher.utils.Utils;
import com.salsal.school.teacher.webservice.APIErrorResult;
import com.salsal.school.teacher.webservice.CallbackHandler;
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
        WebServiceHelper.get(this).getTeacherProfile(PreferenceManager.getUserProfile(this).get(PreferenceManager.PREF_USER_ID), PreferenceManager.getUserProfile(this).get(PreferenceManager.PREF_TOKEN))
                .enqueue(new CallbackHandler<TeacherProfileRes>(this, true, true) {
                    @Override
                    public void onSuccess(Response<TeacherProfileRes> response) {
                        edtBirthday.setText(Utils.convertBirthdayToString(response.body().getData().getBirthDate()));
                        edtEducation.setText(response.body().getData().getEducation());
                        edtEmail.setText(response.body().getData().getEmail());
                        edtPhone.setText(response.body().getData().getPhoneNumber());
                        edtBirthday.setTag(response.body().getData().getBirthDate());

                    }

                    @Override
                    public void onFailed(APIErrorResult errorResult) {

                    }
                });

    }

 /*   private int convertBirthdayToInt(String Birthday) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(edtBirthday.getText().toString()itemList.getStartTime());
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        String startTime = dateFormat.format(itemList.getStartTime() * 1000L);
        String endTime = dateFormat.format(itemList.getEndTime() * 1000L);
        return 0;
    }*/



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSend:
                TeacherProfileReq teacherProfileReq = new TeacherProfileReq();
                teacherProfileReq.setBirthDate((Long) edtBirthday.getTag());
                teacherProfileReq.setEmail(edtEmail.getText().toString());
                teacherProfileReq.setPhoneNumber(edtPhone.getText().toString());
                teacherProfileReq.setEducation(edtEducation.getText().toString());
                WebServiceHelper.get(ActivityEditProfile.this).updateProfile(PreferenceManager.getUserProfile(ActivityEditProfile.this).get(PreferenceManager.PREF_USER_ID),
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
        edtBirthday.setTag(persianCalendar.getTimeInMillis()/1000);
    }

    @Override
    public void onDismissed() {

    }
}

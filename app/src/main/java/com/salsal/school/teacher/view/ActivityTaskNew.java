package com.salsal.school.teacher.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.salsal.school.teacher.R;
import com.salsal.school.teacher.interfaces.APIErrorResult;
import com.salsal.school.teacher.interfaces.CallbackHandler;
import com.salsal.school.teacher.model.ActivityDetailsRes;
import com.salsal.school.teacher.model.ActivityReq;
import com.salsal.school.teacher.model.SpnType;
import com.salsal.school.teacher.model.StudentRes;
import com.salsal.school.teacher.utils.PreferenceManager;
import com.salsal.school.teacher.webservice.WebServiceHelper;

import java.sql.Timestamp;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.hamsaa.persiandatepicker.Listener;
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog;
import ir.hamsaa.persiandatepicker.util.PersianCalendar;
import retrofit2.Response;

/**
 * Created by Sepehr on 12/4/2017.
 */

public class ActivityTaskNew extends BaseActivity implements View.OnClickListener, Listener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edtTitle)
    AppCompatEditText edtTitle;
    @BindView(R.id.edtDesc)
    AppCompatEditText edtDesc;
    @BindView(R.id.spnActivity)
    AppCompatSpinner spnActivity;
    @BindView(R.id.btnDate)
    ImageView btnDate;
    @BindView(R.id.btnSend)
    AppCompatButton btnSend;
    @BindView(R.id.txtDate)
    TextView txtDate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_activity);
        ButterKnife.bind(this);
        btnDate.setOnClickListener(this);
        btnSend.setOnClickListener(this);
        toolbar.setTitle("فعالیت جدید");
        ArrayList<SpnType> notifTypes = new ArrayList<>();
        String[] testArray = getResources().getStringArray(R.array.activity_type);

        for (int i = 0; i < testArray.length; i++) {
            notifTypes.add(new SpnType(testArray[i].split("-")[0], testArray[i].split("-")[1]));
        }

        ArrayAdapter<SpnType> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, notifTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnActivity.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDate:
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
            case R.id.btnSend:
                WebServiceHelper.get(this).getStudentsClass(PreferenceManager.getUserProfile(this).get(PreferenceManager.PREF_TOKEN), PreferenceManager.getTeacherClassId(this))
                        .enqueue(new CallbackHandler<StudentRes>(this, true, true) {
                            @Override
                            public void onSuccess(Response<StudentRes> response) {
                                ActivityReq activityReq = new ActivityReq();
                                activityReq.setClassId(PreferenceManager.getTeacherClassId(ActivityTaskNew.this));
                                activityReq.setCourseId(PreferenceManager.getTeacherCourseId(ActivityTaskNew.this));
                                activityReq.setDesc(edtDesc.getText().toString());
                                activityReq.setTitle(edtTitle.getText().toString());
                                activityReq.setExpireDate(txtDate.getText().toString());
                                activityReq.setFileAddress("link");
                                activityReq.setAtypeId(((SpnType) (spnActivity.getSelectedItem())).getId());
                                ArrayList<Integer> users = new ArrayList<>();
                                for (int i = 0; i < response.body().getData().size(); i++) {
                                    users.add(response.body().getData().get(i).getUserId());
                                }
                                activityReq.setUsers(users);

                                WebServiceHelper.get(ActivityTaskNew.this).createActivity(PreferenceManager.getUserProfile(ActivityTaskNew.this).get(PreferenceManager.PREF_TOKEN), activityReq)
                                        .enqueue(new CallbackHandler<ActivityDetailsRes>(ActivityTaskNew.this, true, true) {
                                            @Override
                                            public void onSuccess(Response<ActivityDetailsRes> response) {
                                                Toast.makeText(ActivityTaskNew.this, "فعالیت با موفقیت ثبت شد", Toast.LENGTH_SHORT).show();
                                            }

                                            @Override
                                            public void onFailed(APIErrorResult errorResult) {

                                            }
                                        });

                            }

                            @Override
                            public void onFailed(APIErrorResult errorResult) {

                            }
                        });

                break;
        }
    }

    @Override
    public void onDateSelected(PersianCalendar persianCalendar) {
        txtDate.setText(persianCalendar.getPersianYear() + "/" + persianCalendar.getPersianMonth() + "/" + persianCalendar.getPersianDay());
        // timestamp = new Timestamp(persianCalendar.getTimeInMillis() / 1000);
    }

    @Override
    public void onDismissed() {

    }
}

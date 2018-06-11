package com.salsal.school.teacher.view.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.salsal.school.teacher.R;
import com.salsal.school.teacher.adapter.AdapterStudents;
import com.salsal.school.teacher.webservice.APIErrorResult;
import com.salsal.school.teacher.webservice.CallbackHandler;
import com.salsal.school.teacher.interfaces.OnSwitchSelectListener;
import com.salsal.school.teacher.model.AttendanceReq;
import com.salsal.school.teacher.model.AttendanceRes;
import com.salsal.school.teacher.model.StudentRes;
import com.salsal.school.teacher.utils.PreferenceManager;
import com.salsal.school.teacher.view.BaseActivity;
import com.salsal.school.teacher.webservice.WebServiceHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;

public class ActivityAttendance extends BaseActivity implements OnSwitchSelectListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.list)
    RecyclerView list;
    @BindView(R.id.fabAddNotif)
    FloatingActionButton fabAddNotif;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atendances);
        ButterKnife.bind(this);
        toolbar.setTitle("لیست دانش آموزان");
        LinearLayoutManager layoutManager = new LinearLayoutManager(ActivityAttendance.this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, layoutManager.getOrientation());
        list.setLayoutManager(layoutManager);
        list.addItemDecoration(dividerItemDecoration);
        WebServiceHelper.get(this).getStudentsClass(PreferenceManager.getUserProfile(this).get(PreferenceManager.PREF_TOKEN), PreferenceManager.getTeacherClassId(this))
                .enqueue(new CallbackHandler<StudentRes>(this, true, true) {
                    @Override
                    public void onSuccess(Response<StudentRes> response) {
                        AdapterStudents adapterStudents = new AdapterStudents(response.body().getData(), ActivityAttendance.this);
                        list.setAdapter(adapterStudents);
                    }

                    @Override
                    public void onFailed(APIErrorResult errorResult) {

                    }
                });

    }

    @Override
    public void switched(StudentRes.DataBean data, boolean isOn) {
        AttendanceReq attendanceReq = new AttendanceReq();
        attendanceReq.setClassId(PreferenceManager.getTeacherClassId(this));
        attendanceReq.setCourseId(PreferenceManager.getTeacherCourseId(this));
        attendanceReq.setStatus(isOn ? 1 : 0);
        attendanceReq.setStudentId(data.getId());
        WebServiceHelper.get(this).attendanceStudent(PreferenceManager.getUserProfile(this).get(PreferenceManager.PREF_TOKEN), attendanceReq)
                .enqueue(new CallbackHandler<AttendanceRes>(ActivityAttendance.this, true, true) {
                    @Override
                    public void onSuccess(Response<AttendanceRes> response) {
                        Toast.makeText(ActivityAttendance.this, "اطلاعات با موفقیت ثبت شد", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailed(APIErrorResult errorResult) {

                    }
                });
    }
}

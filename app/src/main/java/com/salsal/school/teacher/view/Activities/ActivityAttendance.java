package com.salsal.school.teacher.view.Activities;

import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.widget.Toast;

import com.salsal.school.teacher.R;
import com.salsal.school.teacher.adapter.AdapterAttendance;
import com.salsal.school.teacher.webservice.APIErrorResult;
import com.salsal.school.teacher.webservice.CallbackHandler;
import com.salsal.school.teacher.interfaces.OnStudentAttendencedListener;
import com.salsal.school.teacher.model.AttendanceReq;
import com.salsal.school.teacher.model.AttendanceRes;
import com.salsal.school.teacher.model.StudentRes;
import com.salsal.school.teacher.utils.PreferenceManager;
import com.salsal.school.teacher.view.BaseActivity;
import com.salsal.school.teacher.webservice.WebServiceHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;

public class ActivityAttendance extends BaseActivity implements OnStudentAttendencedListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.list)
    RecyclerView list;
    @BindView(R.id.fabAddNotif)
    FloatingActionButton fabAddNotif;
    public static final int MODE_ABSENT = 0;
    public static final int MODE_ALIVE = 1;
    public static final int MODE_LATE = 2;

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
                        AdapterAttendance adapterStudents = new AdapterAttendance(response.body().getData(), ActivityAttendance.this);
                        list.setAdapter(adapterStudents);
                    }

                    @Override
                    public void onFailed(APIErrorResult errorResult) {

                    }
                });

    }

    @Override
    public void switched(StudentRes.DataBean data, boolean isAlive, String delay) {
        AttendanceReq attendanceReq = new AttendanceReq();
        attendanceReq.setClassId(PreferenceManager.getTeacherClassId(this));
        attendanceReq.setCourseId(PreferenceManager.getTeacherCourseId(this));
        attendanceReq.setStatus(isAlive ? 1 : 0);
        attendanceReq.setStudentId(data.getId());
        attendanceReq.setDelay(delay);
        WebServiceHelper.get(this).attendanceStudent(PreferenceManager.getUserProfile(this).get(PreferenceManager.PREF_TOKEN), attendanceReq)
                .enqueue(new CallbackHandler<AttendanceRes>(ActivityAttendance.this, true, true) {
                    @Override
                    public void onSuccess(Response<AttendanceRes> response) {
                        Toast.makeText(ActivityAttendance.this, R.string.toast_success_saved, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailed(APIErrorResult errorResult) {

                    }
                });
    }
}

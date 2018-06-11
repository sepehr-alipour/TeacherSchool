package com.salsal.school.teacher.view.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.salsal.school.teacher.R;
import com.salsal.school.teacher.adapter.AdapterActivities;
import com.salsal.school.teacher.webservice.APIErrorResult;
import com.salsal.school.teacher.webservice.CallbackHandler;
import com.salsal.school.teacher.interfaces.OnDataSelectListener;
import com.salsal.school.teacher.model.ActivityRes;
import com.salsal.school.teacher.utils.PreferenceManager;
import com.salsal.school.teacher.view.BaseActivity;
import com.salsal.school.teacher.webservice.WebServiceHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;

public class ActivityActivities extends BaseActivity implements OnDataSelectListener, View.OnClickListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.list)
    RecyclerView list;
    @BindView(R.id.fabAddNotif)
    FloatingActionButton fabAddNotif;
    public static final String INTENT_KEY_COURSE_ID = "course_id";
    public static final String INTENT_KEY_CLASS_ID = "class_id";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities);
        ButterKnife.bind(this);
        int courseId = getIntent().getIntExtra(INTENT_KEY_COURSE_ID, -1);
        int classId = getIntent().getIntExtra(INTENT_KEY_CLASS_ID, -1);
        fabAddNotif.setOnClickListener(this);
        toolbar.setTitle("لیست فعالیت ها");
        list.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0)
                    fabAddNotif.hide();
                else if (dy < 0)
                    fabAddNotif.show();
            }
        });
        WebServiceHelper.get(this).getTeacherActivities(PreferenceManager.getUserProfile(this).get(PreferenceManager.PREF_TOKEN), courseId, classId)
                .enqueue(new CallbackHandler<ActivityRes>(this, true, true) {
                    @Override
                    public void onSuccess(Response<ActivityRes> response) {
                        Log.i("test", "onSuccess: ");
                        AdapterActivities adapterActivities = new AdapterActivities(response.body().getData(), ActivityActivities.this);
                        list.setLayoutManager(new LinearLayoutManager(ActivityActivities.this));
                        list.setAdapter(adapterActivities);
                    }

                    @Override
                    public void onFailed(APIErrorResult errorResult) {
                        Log.i("test", "onFailed: ");
                    }
                });

    }

    @Override
    public void dataSelected(Object data) {

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {


            case R.id.fabAddNotif:

                startActivity(new Intent(this, ActivityTaskNew.class));
                break;
        }
    }
}

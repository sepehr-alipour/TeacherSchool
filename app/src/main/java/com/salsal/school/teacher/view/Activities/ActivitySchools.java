package com.salsal.school.teacher.view.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.AppCompatButton;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.salsal.school.teacher.R;
import com.salsal.school.teacher.adapter.AdapterSpinnerSchool;
import com.salsal.school.teacher.model.LoginReq;
import com.salsal.school.teacher.model.SchoolListRes;
import com.salsal.school.teacher.utils.PreferenceManager;
import com.salsal.school.teacher.view.BaseActivity;
import com.salsal.school.teacher.webservice.APIErrorResult;
import com.salsal.school.teacher.webservice.CallbackHandler;
import com.salsal.school.teacher.webservice.WebServiceHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;


public class ActivitySchools extends BaseActivity {

    @BindView(R.id.edtAutoSchools)
    AppCompatAutoCompleteTextView edtAutoSchools;
    @BindView(R.id.btnLogin)
    AppCompatButton btnLogin;
    private SchoolListRes selectedSchool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schools);
        ButterKnife.bind(this);

        final ArrayList<SchoolListRes> list = new ArrayList<>();
        final AdapterSpinnerSchool adapter = new AdapterSpinnerSchool(this, list);
        edtAutoSchools.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedSchool = (SchoolListRes) parent.getItemAtPosition(position);
                Toast.makeText(ActivitySchools.this, selectedSchool.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        edtAutoSchools.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                if (edtAutoSchools.isPerformingCompletion()) {
                    return;
                }
                if (charSequence.length() < 2) {
                    return;
                }
                String query = charSequence.toString();
                WebServiceHelper.get(getString(R.string.BASE_URL)).getSchools(query).enqueue(new CallbackHandler<List<SchoolListRes>>(ActivitySchools.this, false, false)

                {
                    @Override
                    public void onSuccess(Response<List<SchoolListRes>> response) {
                        //list.add(response.body());
                        adapter.clear();

                        for (int i = 0; i < response.body().size(); i++) {

                            adapter.add(response.body().get(i));
                        }
                    }

                    @Override
                    public void onFailed(APIErrorResult errorResult) {

                    }
                });

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                if (selectedSchool != null) {
                    LoginReq loginReq = new LoginReq();
                    loginReq.setConnectionUrl(selectedSchool.getUrl());
                    loginReq.setSchoolLogo(selectedSchool.getLogo());
                    loginReq.setChecked(true);
                    loginReq.setSchoolName(selectedSchool.getName());
                    loginReq.setId(PreferenceManager.getSchoolConnections().size() + 1);
                    //todo
                    if (true) {//PreferenceManager.addSchoolConnection(loginReq)) {
                        startActivity(new Intent(ActivitySchools.this, ActivityLogin.class));
                    }
                    Toast.makeText(ActivitySchools.this, R.string.toast_warning_duplicate_school, Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(ActivitySchools.this, R.string.toast_error_school, Toast.LENGTH_SHORT).show();
            }
        });
        edtAutoSchools.setAdapter(adapter);

    }
}

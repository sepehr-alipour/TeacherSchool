package com.salsal.school.teacher.view.Activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.salsal.school.teacher.R;
import com.salsal.school.teacher.model.UnitAdmins;
import com.salsal.school.teacher.webservice.APIErrorResult;
import com.salsal.school.teacher.webservice.CallbackHandler;
import com.salsal.school.teacher.model.LoginReq;
import com.salsal.school.teacher.model.LoginRes;
import com.salsal.school.teacher.utils.PreferenceManager;
import com.salsal.school.teacher.view.BaseActivity;
import com.salsal.school.teacher.webservice.WebServiceHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;

/**
 * Created by Sepehr on 12/4/2017.
 */

public class ActivityLogin extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.edtUsername)
    AppCompatEditText edtUsername;
    @BindView(R.id.edtPassword)
    AppCompatEditText edtPassword;
    @BindView(R.id.btnLogin)
    AppCompatButton btnLogin;
    private boolean inEdit = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        btnLogin.setOnClickListener(this);

        LoginReq schoolConnection = PreferenceManager.getSchoolConnection(getIntent().getIntExtra(ActivityAddSchool.INTENT_KEY_SCHOOL_ID, -1));
        if (schoolConnection != null) {
            inEdit = true;
            edtPassword.setText(schoolConnection.getPassword());
            edtUsername.setText(schoolConnection.getUsername());
        }
    }

    @Override
    public void onClick(View v) {


        if (TextUtils.isEmpty(edtPassword.getText().toString()) ||
                TextUtils.isEmpty(edtUsername.getText().toString()) ||
                TextUtils.isEmpty(edtUsername.getText().toString())) {

            Toast.makeText(ActivityLogin.this, getString(R.string.toast_empty_edittext), Toast.LENGTH_SHORT).show();
        } else {
            final LoginReq loginReq = PreferenceManager.getSchoolConnection(PreferenceManager.getSchoolConnections().size());
            loginReq.setUsername(edtUsername.getText().toString());
            loginReq.setPassword(edtPassword.getText().toString());
            if (inEdit) {
                int id = getIntent().getIntExtra(ActivityAddSchool.INTENT_KEY_SCHOOL_ID, -1);
                loginReq.setId(PreferenceManager.getSchoolConnection(id).getId());
                loginReq.setChecked(PreferenceManager.getSchoolConnection(id).isChecked());
                PreferenceManager.updateSchoolConnection(loginReq);

                setResult(RESULT_OK);
                finish();
            } else {

                loginReq.setChecked(true);
                PreferenceManager.updateSchoolConnection(loginReq);
                WebServiceHelper.get(ActivityLogin.this).loginUser(loginReq).enqueue(new CallbackHandler<LoginRes>(ActivityLogin.this, true, true) {
                    @Override
                    public void onSuccess(Response<LoginRes> response) {
                        getAdminId(response.body().getData().getToken());
                        PreferenceManager.SaveUserProfile(ActivityLogin.this,
                                response.body().getData().getUserId(),
                                response.body().getData().getToken());
                        Intent intent = new Intent(ActivityLogin.this, ActivityMain.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onFailed(APIErrorResult errorResult) {
                        PreferenceManager.removeSchoolConnection(loginReq);
                        Toast.makeText(ActivityLogin.this, errorResult.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }

    }

    private void getAdminId(String token) {
        WebServiceHelper.get(this).getAdmins(token).enqueue(new CallbackHandler<UnitAdmins>(this, true, true) {
            @Override
            public void onSuccess(Response<UnitAdmins> response) {
                PreferenceManager.saveAdminId(ActivityLogin.this, response.body().getData().get(0).getId());
            }

            @Override
            public void onFailed(APIErrorResult errorResult) {

            }
        });
    }
}

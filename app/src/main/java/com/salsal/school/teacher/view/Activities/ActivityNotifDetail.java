package com.salsal.school.teacher.view.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.salsal.school.teacher.R;
import com.salsal.school.teacher.interfaces.APIErrorResult;
import com.salsal.school.teacher.interfaces.CallbackHandler;
import com.salsal.school.teacher.model.NotificationDetailRes;
import com.salsal.school.teacher.utils.PreferenceManager;
import com.salsal.school.teacher.view.BaseActivity;
import com.salsal.school.teacher.webservice.WebServiceHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;

public class ActivityNotifDetail extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.txtTitle)
    TextView txtTitle;
    @BindView(R.id.txtDate)
    TextView txtDate;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.txtDesc)
    TextView txtDesc;
    @BindView(R.id.txtType)
    TextView txtType;
    @BindView(R.id.txtSender)
    TextView txtSender;
    @BindView(R.id.edtResponse)
    AppCompatEditText edtResponse;
    @BindView(R.id.btnSend)
    AppCompatButton btnSend;
    private int notifId;
    public static final String INTENT_NOTIF_ID = "notif_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notif_detail);
        ButterKnife.bind(this);
        notifId = getIntent().getIntExtra(INTENT_NOTIF_ID, 0);

        WebServiceHelper.get(this).getNotificationDetails(notifId, PreferenceManager.getUserProfile(this).get(PreferenceManager.PREF_TOKEN))
                .enqueue(new CallbackHandler<NotificationDetailRes>(this, true, true) {
                    @Override
                    public void onSuccess(Response<NotificationDetailRes> response) {
                        txtDate.setText(response.body().getData().getCreatedAt());
                        txtDesc.setText(response.body().getData().getMessage());
                        txtSender.setText(response.body().getData().getId()+"");
                        txtTitle.setText(response.body().getData().getTitle());
                        txtType.setText(response.body().getData().getType()+"");
                    }

                    @Override
                    public void onFailed(APIErrorResult errorResult) {

                    }
                });
    }
}

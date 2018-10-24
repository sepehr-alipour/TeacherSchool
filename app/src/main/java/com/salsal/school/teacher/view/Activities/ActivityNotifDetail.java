package com.salsal.school.teacher.view.Activities;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.salsal.school.teacher.R;
import com.salsal.school.teacher.model.SendNotificationReq;
import com.salsal.school.teacher.utils.Utils;
import com.salsal.school.teacher.webservice.APIErrorResult;
import com.salsal.school.teacher.webservice.CallbackHandler;
import com.salsal.school.teacher.model.NotificationDetailRes;
import com.salsal.school.teacher.utils.PreferenceManager;
import com.salsal.school.teacher.view.BaseActivity;
import com.salsal.school.teacher.webservice.WebServiceHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;

public class ActivityNotifDetail extends BaseActivity implements View.OnClickListener {

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
    private NotificationDetailRes.DataBean notifDetail;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notif_detail);
        ButterKnife.bind(this);
        btnSend.setOnClickListener(this);
        notifId = getIntent().getIntExtra(INTENT_NOTIF_ID, -1);
        token = PreferenceManager.getUserProfile(this).get(PreferenceManager.PREF_TOKEN);

        WebServiceHelper.get(this).getNotificationDetails(notifId, token)
                .enqueue(new CallbackHandler<NotificationDetailRes>(this, true, true) {
                    @Override
                    public void onSuccess(Response<NotificationDetailRes> response) {
                        notifDetail = response.body().getData().get(0);
                        txtDate.setText(Utils.convertBirthdayToString(notifDetail.getCreatedAt()));
                        txtDesc.setText(notifDetail.getMessage());
                        txtSender.setText(notifDetail.getSenderName() + "(" + notifDetail.getRoleTitle() + ")");
                        txtTitle.setText(notifDetail.getTitle());
                        txtType.setText(notifDetail.getType() + "");
                        String[] testArray = txtType.getContext().getResources().getStringArray(R.array.notification_type);

                        switch (notifDetail.getType()) {
                            case 1:
                                txtType.setText(testArray[0].split("-")[0]);
                                break;
                            case 2:
                                txtType.setText(testArray[1].split("-")[0]);
                                break;
                        }

                    }

                    @Override
                    public void onFailed(APIErrorResult errorResult) {

                    }
                });
    }

    @Override
    public void onClick(View v) {
        List<Integer> userId = new ArrayList<>();
        userId.add(notifDetail.getSenderId());
        SendNotificationReq notificationReq = new SendNotificationReq();
        notificationReq.setMessage(edtResponse.getText().toString());
        notificationReq.setRecipientType(notifDetail.getRecipientType());
        notificationReq.setUserIds(userId);
        notificationReq.setFileUrl("link");
        notificationReq.setTitle(notifDetail.getTitle());
        notificationReq.setType(notifDetail.getType());
        WebServiceHelper.get(this).sendNotification(token, notificationReq).enqueue(new CallbackHandler<NotificationDetailRes>(this, true, true) {
            @Override
            public void onSuccess(Response<NotificationDetailRes> response) {
                Toast.makeText(ActivityNotifDetail.this, R.string.toast_success_notif, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailed(APIErrorResult errorResult) {

            }
        });
    }
}

package com.salsal.school.teacher.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.salsal.school.teacher.R;

import butterknife.BindView;
import butterknife.ButterKnife;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notif_detail);
        ButterKnife.bind(this);
        notifId = getIntent().getIntExtra("notifId", 0);
    }
}

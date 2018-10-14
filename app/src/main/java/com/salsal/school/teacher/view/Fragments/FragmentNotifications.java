package com.salsal.school.teacher.view.Fragments;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.salsal.school.teacher.R;
import com.salsal.school.teacher.adapter.AdapterNofits;
import com.salsal.school.teacher.webservice.APIErrorResult;
import com.salsal.school.teacher.webservice.CallbackHandler;
import com.salsal.school.teacher.interfaces.OnNotifClickListener;
import com.salsal.school.teacher.model.NotificationRes;
import com.salsal.school.teacher.utils.PreferenceManager;
import com.salsal.school.teacher.view.Activities.ActivityNotifDetail;
import com.salsal.school.teacher.view.Activities.ActivityNotificationNew;
import com.salsal.school.teacher.view.BaseFragment;
import com.salsal.school.teacher.webservice.WebServiceHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Response;

public class FragmentNotifications extends BaseFragment implements OnNotifClickListener, View.OnClickListener {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.notifList)
    RecyclerView notifList;
    Unbinder unbinder;
    @BindView(R.id.fabAddNotif)
    FloatingActionButton fabAddNotif;
    @BindView(R.id.receivedMessageTab)
    Button receivedMessageTab;
    @BindView(R.id.sentMessageTab)
    Button sentMessageTab;
    @BindView(R.id.tabLayout)
    LinearLayout tabLayout;

    private String mParam1;
    private String mParam2;


    public FragmentNotifications() {
        // Required empty public constructor
    }

    public static FragmentNotifications newInstance(String param1, String param2) {
        FragmentNotifications fragment = new FragmentNotifications();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);
        unbinder = ButterKnife.bind(this, view);
        fabAddNotif.setOnClickListener(this);
        receivedMessageTab.setOnClickListener(this);
        sentMessageTab.setOnClickListener(this);
        setStyle(0);
        notifList.setLayoutManager(new LinearLayoutManager(getContext()));
        notifList.addOnScrollListener(new RecyclerView.OnScrollListener() {
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


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void clicked(NotificationRes.DataBean notification) {
        if ((notification.getUserId() + "").equalsIgnoreCase(PreferenceManager.getUserProfile(getContext()).get(PreferenceManager.PREF_USER_ID))) {
            return;
        }
        Intent intent = new Intent(getContext(), ActivityNotifDetail.class);
        intent.putExtra(ActivityNotifDetail.INTENT_NOTIF_ID, notification.getId());
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {


            case R.id.fabAddNotif:

                startActivity(new Intent(getContext(), ActivityNotificationNew.class));
                break;
            case R.id.receivedMessageTab:
                setStyle(0);
                break;
            case R.id.sentMessageTab:
                setStyle(1);
                break;
        }
    }

    private void setStyle(int pos) {
        if (pos == 0) {
            receivedMessageTab.setBackgroundResource(R.drawable.shape_blue_fill);
            receivedMessageTab.setTextColor(getResources().getColor(android.R.color.white));
            sentMessageTab.setBackgroundResource(R.drawable.shape_blue_blank);
            sentMessageTab.setTextColor(getResources().getColor(R.color.colorAccent));

            WebServiceHelper.get(getContext()).getNotifications(PreferenceManager.getUserProfile(getContext()).get(PreferenceManager.PREF_TOKEN))
                    .enqueue(new CallbackHandler<NotificationRes>(getContext(), true, true) {
                        @Override
                        public void onSuccess(Response<NotificationRes> response) {
                            notifList.setAdapter(new AdapterNofits(response.body().getData(), FragmentNotifications.this));
                        }

                        @Override
                        public void onFailed(APIErrorResult errorResult) {

                        }
                    });
        } else {
            sentMessageTab.setBackgroundResource(R.drawable.shape_blue_fill);
            sentMessageTab.setTextColor(getResources().getColor(android.R.color.white));
            receivedMessageTab.setBackgroundResource(R.drawable.shape_blue_blank);
            receivedMessageTab.setTextColor(getResources().getColor(R.color.colorAccent));

            WebServiceHelper.get(getContext()).getSentBoxNotifications(PreferenceManager.getUserProfile(getContext()).get(PreferenceManager.PREF_TOKEN))
                    .enqueue(new CallbackHandler<NotificationRes>(getContext(), true, true) {
                        @Override
                        public void onSuccess(Response<NotificationRes> response) {
                            notifList.setAdapter(new AdapterNofits(response.body().getData(), FragmentNotifications.this));
                        }

                        @Override
                        public void onFailed(APIErrorResult errorResult) {

                        }
                    });
        }
    }
}

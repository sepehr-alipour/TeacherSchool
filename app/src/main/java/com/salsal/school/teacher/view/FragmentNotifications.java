package com.salsal.school.teacher.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.salsal.school.teacher.R;
import com.salsal.school.teacher.adapter.AdapterNofits;
import com.salsal.school.teacher.interfaces.APIErrorResult;
import com.salsal.school.teacher.interfaces.CallbackHandler;
import com.salsal.school.teacher.interfaces.OnNotifClickListener;
import com.salsal.school.teacher.model.ClsNotification;
import com.salsal.school.teacher.webservice.ValueKeeper;
import com.salsal.school.teacher.webservice.WebServiceHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Response;

public class FragmentNotifications extends Fragment implements OnNotifClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.notifList)
    RecyclerView notifList;
    Unbinder unbinder;
    @BindView(R.id.fabAddNotif)
    FloatingActionButton fabAddNotif;

    // TODO: Rename and change types of parameters
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
        WebServiceHelper.get(getContext()).getNotifications(ValueKeeper.getUserProfile(getContext()).get(ValueKeeper.PREF_TOKEN))
                .enqueue(new CallbackHandler<ClsNotification>(getContext(), true, true) {
                    @Override
                    public void onSuccess(Response<ClsNotification> response) {
                        notifList.setAdapter(new AdapterNofits(response.body().getData(), FragmentNotifications.this));
                    }

                    @Override
                    public void onFailed(APIErrorResult errorResult) {

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
    public void clicked(ClsNotification.DataBean notification) {
        Intent intent = new Intent(getContext(), ActivityNotifDetail.class);
        intent.putExtra("notifId", notification.getId());
        startActivity(intent);
    }
}

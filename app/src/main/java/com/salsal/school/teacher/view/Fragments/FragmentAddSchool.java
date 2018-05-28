package com.salsal.school.teacher.view.Fragments;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.salsal.school.teacher.R;
import com.salsal.school.teacher.adapter.AdapterSchools;
import com.salsal.school.teacher.interfaces.APIErrorResult;
import com.salsal.school.teacher.interfaces.CallbackHandler;
import com.salsal.school.teacher.interfaces.OnDataSelectListener;
import com.salsal.school.teacher.model.LoginReq;
import com.salsal.school.teacher.model.LoginRes;
import com.salsal.school.teacher.utils.PreferenceManager;
import com.salsal.school.teacher.view.Activities.ActivityLogin;
import com.salsal.school.teacher.view.BaseFragment;
import com.salsal.school.teacher.webservice.WebServiceHelper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Response;

public class FragmentAddSchool extends BaseFragment implements OnDataSelectListener {

    @BindView(R.id.list)
    RecyclerView list;
    Unbinder unbinder;
    private AdapterSchools adapterSchools;
    private ArrayList<LoginReq> schools;
    public static final String INTENT_KEY_SCHOOL_ID = "school_id";

    public static FragmentAddSchool newInstance(String param1, String param2) {
        FragmentAddSchool fragment = new FragmentAddSchool();
        Bundle args = new Bundle();
        args.putString("param1", param1);
        args.putString("param2", param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_school, container, false);
        unbinder = ButterKnife.bind(this, view);
        schools = PreferenceManager.getSchoolConnections();
        adapterSchools = new AdapterSchools(schools, this);
        list.setLayoutManager(new LinearLayoutManager(getContext()));
        list.setAdapter(adapterSchools);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        list.setAdapter(new AdapterSchools(PreferenceManager.getSchoolConnections(), FragmentAddSchool.this));
        list.invalidate();
    }

    @Override
    public void dataSelected(final Object data) {
        AlertDialog alert = new AlertDialog.Builder(getContext()).create();
        alert.setMessage("آیا از انتخاب به عنوان مدرسه پیشفرض مطمئن هستید؟");
        alert.setButton(DialogInterface.BUTTON_POSITIVE, "بله", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                WebServiceHelper.get(getContext()).loginUser((LoginReq) data).enqueue(new CallbackHandler<LoginRes>(getContext(), true, true) {
                    @Override
                    public void onSuccess(Response<LoginRes> response) {
                        ((LoginReq) data).setChecked(true);
                        PreferenceManager.addSchoolConnection((LoginReq) data);
                        PreferenceManager.SaveUserProfile(getContext(), response.body().getData().getUserId(), response.body().getData().getToken());
                        list.setAdapter(new AdapterSchools(PreferenceManager.getSchoolConnections(), FragmentAddSchool.this));
                        list.invalidate();

                    }

                    @Override
                    public void onFailed(APIErrorResult errorResult) {

                    }
                });
            }
        });
        alert.setButton(DialogInterface.BUTTON_NEGATIVE, "خیر", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert.setButton(DialogInterface.BUTTON_NEUTRAL, "تغییر اطلاعات", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getContext(), ActivityLogin.class);
                intent.putExtra(INTENT_KEY_SCHOOL_ID, ((LoginReq) data).getId());
                startActivityForResult(intent, 1);
            }
        });
        alert.show();


    }
}

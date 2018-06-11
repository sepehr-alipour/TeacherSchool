package com.salsal.school.teacher.view.Fragments;

import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.AppCompatRatingBar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.salsal.school.teacher.R;
import com.salsal.school.teacher.webservice.APIErrorResult;
import com.salsal.school.teacher.webservice.CallbackHandler;
import com.salsal.school.teacher.model.TeacherProfileRes;
import com.salsal.school.teacher.utils.PreferenceManager;
import com.salsal.school.teacher.view.BaseFragment;
import com.salsal.school.teacher.webservice.WebServiceHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Response;

public class FragmentProfileDetail extends BaseFragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Unbinder unbinder;
    @BindView(R.id.txtEducation)
    TextView txtEducation;
    @BindView(R.id.txtBirthday)
    TextView txtBirthday;
    @BindView(R.id.txtEmail)
    TextView txtEmail;
    @BindView(R.id.txtMobile)
    TextView txtMobile;
    @BindView(R.id.txtNationalCode)
    TextView txtNationalCode;
    @BindView(R.id.ratingBar)
    AppCompatRatingBar ratingBar;
    /*@BindView(R.id.fabEdit)
    FloatingActionButton fabEdit;*/
    @BindView(R.id.list)
    NestedScrollView list;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public FragmentProfileDetail() {
        // Required empty public constructor
    }

    public static FragmentProfileDetail newInstance(String param1, String param2) {
        FragmentProfileDetail fragment = new FragmentProfileDetail();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //  inflater.inflate(R.menu.profile_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_profile_details, container, false);
        unbinder = ButterKnife.bind(this, view);
        WebServiceHelper.get(getContext()).getTeacherProfile(PreferenceManager.getUserProfile(getContext()).get(PreferenceManager.PREF_ID)
                , PreferenceManager.getUserProfile(getContext()).get(PreferenceManager.PREF_TOKEN))
                .enqueue(new CallbackHandler<TeacherProfileRes>(getContext(), true, true) {
                    @Override
                    public void onSuccess(Response<TeacherProfileRes> response) {
                        txtBirthday.setText(response.body().getData().getBirthDate());
                        txtEducation.setText(response.body().getData().getEducation());
                        txtEmail.setText(response.body().getData().getEmail());
                        txtMobile.setText(response.body().getData().getPhoneNumber());
                        txtNationalCode.setText(response.body().getData().getNationalCode());
                        ratingBar.setRating(response.body().getData().getRate());
                        ((FragmentProfile) getParentFragment()).dataLoaded(response);
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fabEdit:

        }

    }
}

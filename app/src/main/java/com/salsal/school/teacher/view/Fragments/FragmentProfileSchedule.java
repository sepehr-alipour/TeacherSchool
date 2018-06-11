package com.salsal.school.teacher.view.Fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.salsal.school.teacher.R;
import com.salsal.school.teacher.adapter.AdapterSchedule;
import com.salsal.school.teacher.webservice.APIErrorResult;
import com.salsal.school.teacher.webservice.CallbackHandler;
import com.salsal.school.teacher.model.ScheduleRes;
import com.salsal.school.teacher.utils.PreferenceManager;
import com.salsal.school.teacher.view.BaseFragment;
import com.salsal.school.teacher.webservice.WebServiceHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Response;

public class FragmentProfileSchedule extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Unbinder unbinder;
    @BindView(R.id.list)
    RecyclerView list;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public FragmentProfileSchedule() {
        // Required empty public constructor
    }

    public static FragmentProfileSchedule newInstance(String param1, String param2) {
        FragmentProfileSchedule fragment = new FragmentProfileSchedule();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.profile_menu, menu);
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

        View view = inflater.inflate(R.layout.fragment_profile_schedule, container, false);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            WebServiceHelper.get(getContext()).getTeacherSchedule(PreferenceManager.getUserProfile(getContext()).get(PreferenceManager.PREF_TOKEN))
                    .enqueue(new CallbackHandler<ScheduleRes>(getContext(), true, true) {
                        @Override
                        public void onSuccess(Response<ScheduleRes> response) {
                            AdapterSchedule adapterSchedule = new AdapterSchedule(response.body().getData());
                            list.setLayoutManager(new LinearLayoutManager(getContext()));
                            list.setAdapter(adapterSchedule);
                        }

                        @Override
                        public void onFailed(APIErrorResult errorResult) {

                        }
                    });
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

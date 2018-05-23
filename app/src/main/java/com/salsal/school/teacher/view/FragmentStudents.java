package com.salsal.school.teacher.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.salsal.school.teacher.R;
import com.salsal.school.teacher.adapter.AdapterGrid;
import com.salsal.school.teacher.interfaces.OnDataSelectListener;
import com.salsal.school.teacher.interfaces.OnViewClickListener;
import com.salsal.school.teacher.model.ClassRes;
import com.salsal.school.teacher.model.GridItem;
import com.salsal.school.teacher.utils.PreferenceManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class FragmentStudents extends Fragment implements OnViewClickListener, OnDataSelectListener {
    // TODO: Rename parameter arguments, choose names that match
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private final int TYPE_ATTENDANCE = 0;
    private final int TYPE_ACTIVITY = 1;
    private final int TYPE_REPORTS = 2;
    private int datatype = -1;
    RecyclerView listStudents;
    @BindView(R.id.bottom_sheet)
    LinearLayout bottomSheet;
    private String[] listName = {"", "asd"};
    Unbinder unbinder;
    @BindView(R.id.gridList)
    RecyclerView gridList;

    private String mParam1;
    private String mParam2;


    public FragmentStudents() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentProfileDetail.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentStudents newInstance(String param1, String param2) {
        FragmentStudents fragment = new FragmentStudents();
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
        View view = inflater.inflate(R.layout.fragment_students, container, false);
        unbinder = ButterKnife.bind(this, view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        AdapterGrid adapterGrid = new AdapterGrid(addItems(), this);
        gridList.setLayoutManager(gridLayoutManager);
        gridList.setHasFixedSize(true);
        gridList.setAdapter(adapterGrid);

        return view;
    }

    private List<GridItem> addItems() {
        List<GridItem> list = new ArrayList<>();

        GridItem gridItemAttendance = new GridItem();
        gridItemAttendance.setTitle("حضور غیاب");
        gridItemAttendance.setIcon(R.drawable.ic_action_edit);
        gridItemAttendance.setType(TYPE_ATTENDANCE);
        list.add(gridItemAttendance);

        GridItem gridItemActivity = new GridItem();
        gridItemActivity.setTitle("فعالیت ها");
        gridItemActivity.setIcon(R.drawable.ic_action_activity);
        gridItemActivity.setType(TYPE_ACTIVITY);
        list.add(gridItemActivity);
        return list;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void clicked(int type, View view) {

        switch (type) {
            case TYPE_ACTIVITY:
                datatype = TYPE_ACTIVITY;
                BottomSheetFragmentStudents bottomSheetFragmentActivity = new BottomSheetFragmentStudents(BottomSheetFragmentStudents.TYPE_COURSES, false, this);
                bottomSheetFragmentActivity.show(getChildFragmentManager(), "students");
                break;
            case TYPE_REPORTS:
                datatype = TYPE_REPORTS;
                break;
            case TYPE_ATTENDANCE:
                datatype = TYPE_ATTENDANCE;
                BottomSheetFragmentStudents bottomSheetFragmentAttendance = new BottomSheetFragmentStudents(BottomSheetFragmentStudents.TYPE_COURSES, false, this);
                bottomSheetFragmentAttendance.show(getChildFragmentManager(), "students");
                break;

        }

    }

    @Override
    public void dataSelected(Object data) {
        switch (datatype) {
            case TYPE_ACTIVITY:
                Intent intent = new Intent(getContext(), ActivityActivities.class);
                intent.putExtra(ActivityActivities.INTENT_KEY_CLASS_ID, ((ClassRes.DataBean) data).getId());
                intent.putExtra(ActivityActivities.INTENT_KEY_COURSE_ID, PreferenceManager.getTeacherCourseId(getContext()));
                startActivity(intent);

                break;
            case TYPE_REPORTS:
                break;
            case TYPE_ATTENDANCE:
                startActivity(new Intent(getContext(), ActivityAttendance.class));

                break;

        }

    }
}

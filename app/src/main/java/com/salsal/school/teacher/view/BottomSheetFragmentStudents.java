package com.salsal.school.teacher.view;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.salsal.school.teacher.R;
import com.salsal.school.teacher.adapter.AdapterCourseList;
import com.salsal.school.teacher.adapter.AdapterReceiverMessageClass;
import com.salsal.school.teacher.adapter.AdapterReceiverMessageStudent;
import com.salsal.school.teacher.interfaces.APIErrorResult;
import com.salsal.school.teacher.interfaces.CallbackHandler;
import com.salsal.school.teacher.interfaces.OnDataSelectListener;
import com.salsal.school.teacher.interfaces.OnReceiverClickListener;
import com.salsal.school.teacher.model.ClassRes;
import com.salsal.school.teacher.model.CourseRes;
import com.salsal.school.teacher.model.ReceiverMessageItem;
import com.salsal.school.teacher.model.StudentRes;
import com.salsal.school.teacher.utils.PreferenceManager;
import com.salsal.school.teacher.webservice.WebServiceHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Response;

@SuppressLint("ValidFragment")
public class BottomSheetFragmentStudents extends BottomSheetDialogFragment implements OnReceiverClickListener, View.OnClickListener {


    private OnDataSelectListener selectDataListener;
    @BindView(R.id.list)
    RecyclerView list;
    Unbinder unbinder;
    private final boolean hasCast;
    public static final int TYEP_STUDENTS = 0;
    public static final int TYPE_CLASSES = 1;
    public static final int TYPE_COURSES = 2;
    @BindView(R.id.btnSendMessage)
    AppCompatButton btnSendMessage;
    private int type = -1;

    ArrayList<StudentRes.DataBean> studentList = new ArrayList<>();

    public BottomSheetFragmentStudents(int type, boolean hasCat) {
        this.type = type;
        this.hasCast = hasCat;
    }

    public BottomSheetFragmentStudents(int type, boolean hasCat, OnDataSelectListener dataSelectListener) {
        this.selectDataListener = dataSelectListener;
        this.type = type;
        this.hasCast = hasCat;
    }

    @Nullable

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_sheet_students, container, false);
        unbinder = ButterKnife.bind(this, view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), layoutManager.getOrientation());
        list.addItemDecoration(dividerItemDecoration);
        list.setLayoutManager(layoutManager);
        btnSendMessage.setOnClickListener(this);
        switch (type) {
            case TYPE_CLASSES:
                getClassList();
                break;
            case TYEP_STUDENTS:
                getStudentList();
                getDialog().setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialogInterface) {
                        BottomSheetDialog d = (BottomSheetDialog) dialogInterface;
                        View bottomSheetInternal = d.findViewById(android.support.design.R.id.design_bottom_sheet);
                        BottomSheetBehavior.from(bottomSheetInternal).setState(BottomSheetBehavior.STATE_EXPANDED);
                    }
                });
                break;
            case TYPE_COURSES:
                getCourseList();
        }
        return view;
    }

    private void getCourseList() {


        WebServiceHelper.get(getContext()).getTecherCourses(PreferenceManager.getUserProfile(getContext()).get(PreferenceManager.PREF_TOKEN))
                .enqueue(new CallbackHandler<CourseRes>(getContext(), true, true) {
                    @Override
                    public void onSuccess(Response<CourseRes> response) {
                        list.setAdapter(new AdapterCourseList(response.body().getData(), BottomSheetFragmentStudents.this));

                    }

                    @Override
                    public void onFailed(APIErrorResult errorResult) {

                    }
                });
    }

    private void getStudentList() {
        WebServiceHelper.get(getContext()).getStudentsClass(PreferenceManager.getUserProfile(getContext()).get(PreferenceManager.PREF_TOKEN), PreferenceManager.getTeacherClassId(getContext()))
                .enqueue(new CallbackHandler<StudentRes>(getContext(), true, true) {
                    @Override
                    public void onSuccess(Response<StudentRes> response) {
                        btnSendMessage.setVisibility(View.VISIBLE);
                        list.setAdapter(new AdapterReceiverMessageStudent(response.body().getData(), BottomSheetFragmentStudents.this));
                    }

                    @Override
                    public void onFailed(APIErrorResult errorResult) {

                    }
                });


    }

    private void getClassList() {
        WebServiceHelper.get(getContext()).getTeacherClasses(PreferenceManager.getUserProfile(getContext()).get(PreferenceManager.PREF_TOKEN), PreferenceManager.getTeacherCourseId(getContext()))
                .enqueue(new CallbackHandler<ClassRes>(getContext(), true, true) {
                    @Override
                    public void onSuccess(Response<ClassRes> response) {
                        list.setAdapter(new AdapterReceiverMessageClass(response.body().getData(), BottomSheetFragmentStudents.this));

                    }

                    @Override
                    public void onFailed(APIErrorResult errorResult) {

                    }
                });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void clicked(ReceiverMessageItem receiverMessageItem, int pos) {

    }

    @Override
    public void clicked(ClassRes.DataBean resClass, int pos) {
        PreferenceManager.saveTeacherClass(getContext(), resClass.getId());

        if (hasCast) {
            BottomSheetFragmentStudents bottomSheetFragmentClasses = new BottomSheetFragmentStudents(BottomSheetFragmentStudents.TYEP_STUDENTS, false,selectDataListener);
            bottomSheetFragmentClasses.show(getChildFragmentManager(), "students");
        } else {
            selectDataListener.dataSelected(resClass);
        }
    }

    @Override
    public void clicked(StudentRes.DataBean studentRes, int pos) {
        if (studentList.contains(studentRes))
            studentList.remove(studentRes);
        else
            studentList.add(studentRes);
    }

    @Override
    public void clicked(CourseRes.DataBean courseRes, int position) {
        PreferenceManager.saveTeacherCourse(getContext(), courseRes.getId());
        BottomSheetFragmentStudents bottomSheetFragmentClasses = new BottomSheetFragmentStudents(BottomSheetFragmentStudents.TYPE_CLASSES, hasCast, selectDataListener);
        bottomSheetFragmentClasses.show(getChildFragmentManager(), "classes");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSendMessage:
                selectDataListener.dataSelected(studentList);

                break;
        }
    }
}

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
import com.salsal.school.teacher.adapter.AdapterReceiverMessageClass;
import com.salsal.school.teacher.adapter.AdapterReceiverMessageStudent;
import com.salsal.school.teacher.interfaces.OnReceiverClickListener;
import com.salsal.school.teacher.model.ClassRes;
import com.salsal.school.teacher.model.ReceiverMessageItem;
import com.salsal.school.teacher.model.StudentRes;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

@SuppressLint("ValidFragment")
public class BottomSheetFragmentStudents extends BottomSheetDialogFragment implements OnReceiverClickListener {


    @BindView(R.id.list)
    RecyclerView list;
    Unbinder unbinder;
    private final boolean hasCast;
    public static final int TYEP_STUDENTS = 0;
    public static final int TYPE_CLASSES = 1;
    @BindView(R.id.btnSendMessage)
    AppCompatButton btnSendMessage;
    private int type = -1;

    public BottomSheetFragmentStudents(int type, boolean hasCat) {
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
        }
        return view;
    }

    private void getStudentList() {
        List<StudentRes> listItemSendto = new ArrayList<>();
        for (int i = 0; i < 50; i++) {

            StudentRes itemStudent = new StudentRes();
            itemStudent.setName("دانش آموز" + i);
            itemStudent.setId(i);
            listItemSendto.add(itemStudent);
        }
        btnSendMessage.setVisibility(View.VISIBLE);
        list.setAdapter(new AdapterReceiverMessageStudent(listItemSendto, this));

    }

    private void getClassList() {

        List<ClassRes> listItemSendto = new ArrayList<>();
        for (int i = 0; i < 10; i++) {

            ClassRes itemClass = new ClassRes("کلاس" + i, 1);
            listItemSendto.add(itemClass);
        }
        list.setAdapter(new AdapterReceiverMessageClass(listItemSendto, this));

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
    public void clicked(ClassRes resClass, int pos) {
        if (hasCast) {
            BottomSheetFragmentStudents bottomSheetFragmentClasses = new BottomSheetFragmentStudents(BottomSheetFragmentStudents.TYEP_STUDENTS, false);
            bottomSheetFragmentClasses.show(getChildFragmentManager(), "students");
        }
    }

    @Override
    public void clicked(StudentRes studentRes, int pos) {

    }
}

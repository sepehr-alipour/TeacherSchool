package com.salsal.school.teacher.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.salsal.school.teacher.R;
import com.salsal.school.teacher.adapter.AdapterReceiverMessage;
import com.salsal.school.teacher.interfaces.OnReceiverClickListener;
import com.salsal.school.teacher.model.ReceiverMessageItem;
import com.salsal.school.teacher.model.ClassRes;
import com.salsal.school.teacher.model.StudentRes;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Sepehr on 12/4/2017.
 */

public class ActivityNotificationNew extends BaseActivity implements View.OnClickListener, OnReceiverClickListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edtTitle)
    AppCompatEditText edtTitle;
    @BindView(R.id.edtMessage)
    AppCompatEditText edtMessage;
    @BindView(R.id.btnSendMessage)
    AppCompatButton btnSendMessage;
    @BindView(R.id.bottom_sheet)
    LinearLayout bottomSheet;
    @BindView(R.id.list)
    RecyclerView listSendto;
    @BindView(R.id.listStudents)
    RecyclerView listStudents;
    private BottomSheetBehavior sheetBehaviorSendto;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_new);
        ButterKnife.bind(this);
        toolbar.setTitle("پیام جدید");

        sheetBehaviorSendto = BottomSheetBehavior.from(bottomSheet);
        btnSendMessage.setOnClickListener(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, layoutManager.getOrientation());
        listSendto.addItemDecoration(dividerItemDecoration);
        listSendto.setLayoutManager(layoutManager);

        List<ReceiverMessageItem> listItemSendto = new ArrayList<>();
        ReceiverMessageItem itemManagement = new ReceiverMessageItem("مدیر", ReceiverMessageItem.UNIT);
        ReceiverMessageItem itemParent = new ReceiverMessageItem("دانش اموز  خاص", ReceiverMessageItem.PARENT);
        ReceiverMessageItem itemClass = new ReceiverMessageItem(" کل کلاس", ReceiverMessageItem.PARENT);
        listItemSendto.add(itemManagement);
        listItemSendto.add(itemParent);
        listItemSendto.add(itemClass);

        listSendto.setAdapter(new AdapterReceiverMessage(listItemSendto, this));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSendMessage:
                if (sheetBehaviorSendto.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    sheetBehaviorSendto.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else {
                    sheetBehaviorSendto.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                break;

        }

    }

    @Override
    public void onBackPressed() {
        if (sheetBehaviorSendto.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            sheetBehaviorSendto.setState(BottomSheetBehavior.STATE_COLLAPSED);
        } else super.onBackPressed();
    }

    @Override
    public void clicked(ReceiverMessageItem receiverMessageItem, int pos) {
        switch (pos) {
            case 0:

                break;
            case 1:
                BottomSheetFragmentStudents bottomSheetFragmentStudents = new BottomSheetFragmentStudents(BottomSheetFragmentStudents.TYPE_CLASSES, true);
                bottomSheetFragmentStudents.show(getSupportFragmentManager(), "students");
                break;
            case 2:
                BottomSheetFragmentStudents bottomSheetFragmentClasses = new BottomSheetFragmentStudents(BottomSheetFragmentStudents.TYPE_CLASSES, false);
                bottomSheetFragmentClasses.show(getSupportFragmentManager(), "classes");

                break;
        }
    }

    @Override
    public void clicked(ClassRes resClass, int pos) {

    }

    @Override
    public void clicked(StudentRes studentRes, int pos) {

    }
}

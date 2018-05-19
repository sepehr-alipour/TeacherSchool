package com.salsal.school.teacher.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.salsal.school.teacher.R;
import com.salsal.school.teacher.adapter.AdapterReceiverMessage;
import com.salsal.school.teacher.adapter.AdapterReceiverMessageClass;
import com.salsal.school.teacher.interfaces.OnReceiverClickListener;
import com.salsal.school.teacher.model.ReceiverMessageItem;
import com.salsal.school.teacher.model.ResClass;

import org.w3c.dom.ls.LSInput;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class BottomSheetFragmentStudents extends BottomSheetDialogFragment implements OnReceiverClickListener {

    @BindView(R.id.list)
    RecyclerView list;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_sheet_students, container, false);
        unbinder = ButterKnife.bind(this, view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), layoutManager.getOrientation());
        list.addItemDecoration(dividerItemDecoration);
        list.setLayoutManager(layoutManager);
        List<ResClass> listItemSendto = new ArrayList<>();
        ResClass itemManagement = new ResClass("مدیر", 1);
        ResClass itemParent = new ResClass("دانش اموز  خاص", 1);
        ResClass itemClass = new ResClass(" کل کلاس", 1);
        listItemSendto.add(itemManagement);
        listItemSendto.add(itemParent);
        listItemSendto.add(itemClass);

        list.setAdapter(new AdapterReceiverMessageClass(listItemSendto, this));
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void clicked(ReceiverMessageItem receiverMessageItem) {

    }

    @Override
    public void clicked(ResClass resClass) {

    }
}

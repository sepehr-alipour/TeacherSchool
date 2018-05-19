package com.salsal.school.teacher.interfaces;

import com.salsal.school.teacher.model.ReceiverMessageItem;
import com.salsal.school.teacher.model.ClassRes;
import com.salsal.school.teacher.model.StudentRes;

public interface OnReceiverClickListener {

    void clicked(ReceiverMessageItem receiverMessageItem,int position);

    void clicked(ClassRes calssRes,int position);

    void clicked(StudentRes studentRes,int position);
}

package com.salsal.school.teacher.interfaces;

import com.salsal.school.teacher.model.ClsNotification;
import com.salsal.school.teacher.model.ReceiverMessageItem;
import com.salsal.school.teacher.model.ResClass;

public interface OnReceiverClickListener {

    void clicked(ReceiverMessageItem receiverMessageItem);

    void clicked(ResClass resClass);
}

package com.salsal.school.teacher.interfaces;

import com.salsal.school.teacher.model.CourseRes;
import com.salsal.school.teacher.model.ReceiverMessageItem;
import com.salsal.school.teacher.model.ClassRes;
import com.salsal.school.teacher.model.StudentRes;

public interface OnReceiverClickListener {

    void clicked(ReceiverMessageItem receiverMessageItem, int position);

    void clicked(ClassRes.DataBean calssRes, int position);

    void clicked(StudentRes.DataBean studentRes, int position);

    void clicked(CourseRes.DataBean courseRes, int position);
}

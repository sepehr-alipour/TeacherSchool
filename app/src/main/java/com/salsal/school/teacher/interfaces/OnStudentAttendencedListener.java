package com.salsal.school.teacher.interfaces;

import com.salsal.school.teacher.model.StudentRes;

public interface OnStudentAttendencedListener {

    void switched(StudentRes.DataBean data, boolean isAlive, String delay);
}

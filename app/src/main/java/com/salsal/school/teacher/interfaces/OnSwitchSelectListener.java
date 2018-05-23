package com.salsal.school.teacher.interfaces;

import com.salsal.school.teacher.model.StudentRes;

public interface OnSwitchSelectListener {

    void switched(StudentRes.DataBean data, boolean isOn);
}

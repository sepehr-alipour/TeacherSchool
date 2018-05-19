package com.salsal.school.teacher.model;

public class ClassRes {
    String name;
    int calssId;

    public ClassRes(String name, int calssId) {
        this.name = name;
        this.calssId = calssId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCalssId() {
        return calssId;
    }

    public void setCalssId(int calssId) {
        this.calssId = calssId;
    }
}

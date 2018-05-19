package com.salsal.school.teacher.model;

public class ResClass {
    String name;
    int calssId;

    public ResClass(String name, int calssId) {
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

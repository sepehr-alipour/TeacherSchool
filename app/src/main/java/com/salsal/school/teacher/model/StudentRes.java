package com.salsal.school.teacher.model;

import java.util.ArrayList;

/**
 * Created by Sepehr on 12/4/2017.
 */

public class StudentRes {
    private int id;
    private String name;
    private String age;
    private ArrayList<ClsLecture> lectures;
    private ArrayList<ClsTask> tasks;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public ArrayList<ClsLecture> getLectures() {
        return lectures;
    }

    public void setLectures(ArrayList<ClsLecture> lectures) {
        this.lectures = lectures;
    }

    public ArrayList<ClsTask> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<ClsTask> tasks) {
        this.tasks = tasks;
    }
}

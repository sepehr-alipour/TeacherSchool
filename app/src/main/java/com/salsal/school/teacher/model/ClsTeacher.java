package com.salsal.school.teacher.model;

import java.util.ArrayList;

/**
 * Created by Sepehr on 12/4/2017.
 */

public class ClsTeacher {
    private int id;
    private String name;
    private String education;
    private ArrayList<ClsLecture> lectures;
    private int rate;
    private String img;
    private String userName;
    private String password;

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

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public ArrayList<ClsLecture> getLectures() {
        return lectures;
    }

    public void setLectures(ArrayList<ClsLecture> lectures) {
        this.lectures = lectures;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

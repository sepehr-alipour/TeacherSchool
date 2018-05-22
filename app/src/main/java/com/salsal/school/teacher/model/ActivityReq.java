package com.salsal.school.teacher.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ActivityReq {


    /**
     * title : title1
     * desc : acdesc1
     * atype_id : 1
     * expire_date : 1396-12-01
     * file_address : url
     * class_id : 1
     * course_id : 2
     * users : [1,2,3]
     */

    @SerializedName("title")
    private String title;
    @SerializedName("desc")
    private String desc;
    @SerializedName("atype_id")
    private String atypeId;
    @SerializedName("expire_date")
    private String expireDate;
    @SerializedName("file_address")
    private String fileAddress;
    @SerializedName("class_id")
    private int classId;
    @SerializedName("course_id")
    private int courseId;
    @SerializedName("users")
    private List<Integer> users;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAtypeId() {
        return atypeId;
    }

    public void setAtypeId(String atypeId) {
        this.atypeId = atypeId;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getFileAddress() {
        return fileAddress;
    }

    public void setFileAddress(String fileAddress) {
        this.fileAddress = fileAddress;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public List<Integer> getUsers() {
        return users;
    }

    public void setUsers(List<Integer> users) {
        this.users = users;
    }
}

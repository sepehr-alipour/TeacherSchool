package com.salsal.school.teacher.model;

import com.google.gson.annotations.SerializedName;

public class AttendanceReq {

    /**
     * student_id : 2
     * course_id : 1
     * class_id : 1
     * status : 0
     */

    @SerializedName("student_id")
    private int studentId;
    @SerializedName("ring_id")
    private int ringId;
    @SerializedName("course_id")
    private int courseId;
    @SerializedName("class_id")
    private int classId;
    @SerializedName("status")
    private int status;
    @SerializedName("delay")
    private String delay;


    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    public String getDelay() {
        return delay;
    }

    public void setDelay(String delay) {
        this.delay = delay;
    }
}

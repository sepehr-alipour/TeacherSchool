package com.salsal.school.teacher.model;

import com.google.gson.annotations.SerializedName;

public class AttendanceRes {

    /**
     * msg : ok
     * data : {"id":1,"student_class_id":2,"course_teacher_id":1,"status":0,"created_at":"2018-05-23 18:29:39","updated_at":"2018-05-23 18:29:39"}
     */

    @SerializedName("msg")
    private String msg;
    @SerializedName("data")
    private DataBean data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * student_class_id : 2
         * course_teacher_id : 1
         * status : 0
         * created_at : 2018-05-23 18:29:39
         * updated_at : 2018-05-23 18:29:39
         */

        @SerializedName("id")
        private int id;
        @SerializedName("student_class_id")
        private int studentClassId;
        @SerializedName("course_teacher_id")
        private int courseTeacherId;
        @SerializedName("status")
        private int status;
        @SerializedName("created_at")
        private String createdAt;
        @SerializedName("updated_at")
        private String updatedAt;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getStudentClassId() {
            return studentClassId;
        }

        public void setStudentClassId(int studentClassId) {
            this.studentClassId = studentClassId;
        }

        public int getCourseTeacherId() {
            return courseTeacherId;
        }

        public void setCourseTeacherId(int courseTeacherId) {
            this.courseTeacherId = courseTeacherId;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }
    }
}

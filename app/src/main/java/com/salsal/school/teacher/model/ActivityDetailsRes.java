package com.salsal.school.teacher.model;

import com.google.gson.annotations.SerializedName;

public class ActivityDetailsRes {


    /**
     * msg : ok
     * data : {"title":"title1","desc":"acdesc1","atype_id":1,"expire_date":"1396-12-01","file_address":"url","teacher_course_class_id":1,"updated_at":"2018-05-22 14:57:53","created_at":"2018-05-22 14:57:53","id":7}
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
         * title : title1
         * desc : acdesc1
         * atype_id : 1
         * expire_date : 1396-12-01
         * file_address : url
         * teacher_course_class_id : 1
         * updated_at : 2018-05-22 14:57:53
         * created_at : 2018-05-22 14:57:53
         * id : 7
         */

        @SerializedName("title")
        private String title;
        @SerializedName("desc")
        private String desc;
        @SerializedName("atype_id")
        private int atypeId;
        @SerializedName("expire_date")
        private String expireDate;
        @SerializedName("file_address")
        private String fileAddress;
        @SerializedName("teacher_course_class_id")
        private int teacherCourseClassId;
        @SerializedName("updated_at")
        private String updatedAt;
        @SerializedName("created_at")
        private String createdAt;
        @SerializedName("id")
        private int id;

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

        public int getAtypeId() {
            return atypeId;
        }

        public void setAtypeId(int atypeId) {
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

        public int getTeacherCourseClassId() {
            return teacherCourseClassId;
        }

        public void setTeacherCourseClassId(int teacherCourseClassId) {
            this.teacherCourseClassId = teacherCourseClassId;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}

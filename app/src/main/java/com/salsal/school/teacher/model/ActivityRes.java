package com.salsal.school.teacher.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ActivityRes {


    /**
     * msg : ok
     * data : [{"id":2,"teacher_course_class_id":1,"title":"title1","desc":"acdesc1","expire_date":"1396-12-01","file_address":"url","atype_id":1,"created_at":"2018-05-21 08:21:15","updated_at":"2018-05-21 08:21:15"},{"id":3,"teacher_course_class_id":1,"title":"title1","desc":"acdesc1","expire_date":"1396-12-01","file_address":"url","atype_id":1,"created_at":"2018-05-21 08:25:06","updated_at":"2018-05-21 08:25:06"},{"id":4,"teacher_course_class_id":1,"title":"title1","desc":"acdesc1","expire_date":"1396-12-01","file_address":"url","atype_id":1,"created_at":"2018-05-21 08:35:24","updated_at":"2018-05-21 08:35:24"},{"id":5,"teacher_course_class_id":1,"title":"title1","desc":"acdesc1","expire_date":"1396-12-01","file_address":"url","atype_id":1,"created_at":"2018-05-21 08:36:20","updated_at":"2018-05-21 08:36:20"},{"id":6,"teacher_course_class_id":1,"title":"title1","desc":"acdesc1","expire_date":"1396-12-01","file_address":"url","atype_id":1,"created_at":"2018-05-21 08:37:26","updated_at":"2018-05-21 08:37:26"}]
     */

    @SerializedName("msg")
    private String msg;
    @SerializedName("data")
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 2
         * teacher_course_class_id : 1
         * title : title1
         * desc : acdesc1
         * expire_date : 1396-12-01
         * file_address : url
         * atype_id : 1
         * created_at : 2018-05-21 08:21:15
         * updated_at : 2018-05-21 08:21:15
         */

        @SerializedName("id")
        private int id;
        @SerializedName("teacher_course_class_id")
        private int teacherCourseClassId;
        @SerializedName("title")
        private String title;
        @SerializedName("desc")
        private String desc;
        @SerializedName("expire_date")
        private String expireDate;
        @SerializedName("file_address")
        private String fileAddress;
        @SerializedName("atype_id")
        private int atypeId;
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

        public int getTeacherCourseClassId() {
            return teacherCourseClassId;
        }

        public void setTeacherCourseClassId(int teacherCourseClassId) {
            this.teacherCourseClassId = teacherCourseClassId;
        }

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

        public int getAtypeId() {
            return atypeId;
        }

        public void setAtypeId(int atypeId) {
            this.atypeId = atypeId;
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

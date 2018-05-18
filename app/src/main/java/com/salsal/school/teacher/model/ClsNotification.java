package com.salsal.school.teacher.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Sepehr on 12/4/2017.
 */

public class ClsNotification {


    /**
     * msg : ok
     * data : [{"id":1,"title":"new notif","message":"متن اعلان","type":1,"file_url":"link","created_at":"2018-05-17 15:14:42","updated_at":"2018-05-17 15:14:42","pivot":{"user_id":1,"notification_id":1,"created_at":"2018-05-17 15:14:42","updated_at":"2018-05-17 15:14:42"}}]
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
         * id : 1
         * title : new notif
         * message : متن اعلان
         * type : 1
         * file_url : link
         * created_at : 2018-05-17 15:14:42
         * updated_at : 2018-05-17 15:14:42
         * pivot : {"user_id":1,"notification_id":1,"created_at":"2018-05-17 15:14:42","updated_at":"2018-05-17 15:14:42"}
         */

        @SerializedName("id")
        private int id;
        @SerializedName("title")
        private String title;
        @SerializedName("message")
        private String message;
        @SerializedName("type")
        private int type;
        @SerializedName("file_url")
        private String fileUrl;
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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getFileUrl() {
            return fileUrl;
        }

        public void setFileUrl(String fileUrl) {
            this.fileUrl = fileUrl;
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

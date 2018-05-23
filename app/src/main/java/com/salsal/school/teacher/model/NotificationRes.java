package com.salsal.school.teacher.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Sepehr on 12/4/2017.
 */

public class NotificationRes {


    /**
     * msg : ok
     * data : [{"id":1,"title":"new year","message":"متن اعلان","type":1,"file_url":"link","recipient_type":"Parent","user_id":1,"created_at":"2018-05-23 15:27:46","updated_at":"2018-05-23 15:27:46"},{"id":2,"title":"new year","message":"متن اعلان","type":1,"file_url":"link","recipient_type":"Parent","user_id":1,"created_at":"2018-05-23 15:29:46","updated_at":"2018-05-23 15:29:46"},{"id":3,"title":"new year","message":"متن اعلان","type":1,"file_url":"link","recipient_type":"Parent","user_id":1,"created_at":"2018-05-23 15:33:57","updated_at":"2018-05-23 15:33:57"}]
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
         * title : new year
         * message : متن اعلان
         * type : 1
         * file_url : link
         * recipient_type : Parent
         * user_id : 1
         * created_at : 2018-05-23 15:27:46
         * updated_at : 2018-05-23 15:27:46
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
        @SerializedName("recipient_type")
        private String recipientType;
        @SerializedName("user_id")
        private int userId;
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

        public String getRecipientType() {
            return recipientType;
        }

        public void setRecipientType(String recipientType) {
            this.recipientType = recipientType;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
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
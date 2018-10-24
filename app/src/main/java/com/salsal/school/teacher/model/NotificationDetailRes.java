package com.salsal.school.teacher.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NotificationDetailRes {


    /**
     * msg : ok
     * data : [{"sender_name":"America","sender_id":1,"role_title":"اولیا","title":"123","message":"456","id":6,"type":1,"recipient_type":"Unit","media_id":"http://google.com","created_at":"1540035018"}]
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
         * sender_name : America
         * sender_id : 1
         * role_title : اولیا
         * title : 123
         * message : 456
         * id : 6
         * type : 1
         * recipient_type : Unit
         * media_id : http://google.com
         * created_at : 1540035018
         */

        @SerializedName("sender_name")
        private String senderName;
        @SerializedName("sender_id")
        private int senderId;
        @SerializedName("role_title")
        private String roleTitle;
        @SerializedName("title")
        private String title;
        @SerializedName("message")
        private String message;
        @SerializedName("id")
        private int id;
        @SerializedName("type")
        private int type;
        @SerializedName("recipient_type")
        private String recipientType;
        @SerializedName("media_id")
        private String mediaId;
        @SerializedName("created_at")
        private long createdAt;

        public String getSenderName() {
            return senderName;
        }

        public void setSenderName(String senderName) {
            this.senderName = senderName;
        }

        public int getSenderId() {
            return senderId;
        }

        public void setSenderId(int senderId) {
            this.senderId = senderId;
        }

        public String getRoleTitle() {
            return roleTitle;
        }

        public void setRoleTitle(String roleTitle) {
            this.roleTitle = roleTitle;
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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getRecipientType() {
            return recipientType;
        }

        public void setRecipientType(String recipientType) {
            this.recipientType = recipientType;
        }

        public String getMediaId() {
            return mediaId;
        }

        public void setMediaId(String mediaId) {
            this.mediaId = mediaId;
        }

        public long getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(long createdAt) {
            this.createdAt = createdAt;
        }
    }
}

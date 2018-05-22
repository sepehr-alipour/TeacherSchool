package com.salsal.school.teacher.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SendNotificationReq {


    /**
     * user_ids : [1,9,10]
     * title : عنوان asdاعل2ان
     * message : متن اعلان
     * type : 1
     * file_url : link
     */

    @SerializedName("title")
    private String title;
    @SerializedName("message")
    private String message;
    @SerializedName("type")
    private String type;
    @SerializedName("file_url")
    private String fileUrl;
    @SerializedName("user_ids")
    private List<Integer> userIds;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public List<Integer> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Integer> userIds) {
        this.userIds = userIds;
    }
}

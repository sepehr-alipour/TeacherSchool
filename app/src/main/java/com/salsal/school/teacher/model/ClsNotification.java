package com.salsal.school.teacher.model;

/**
 * Created by Sepehr on 12/4/2017.
 */

public class ClsNotification {

    private int id;
    private String title;
    private String description;
    private ClsNotificationCat notificationCat;
    private String date;
    private String sender;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ClsNotificationCat getNotificationCat() {
        return notificationCat;
    }

    public void setNotificationCat(ClsNotificationCat notificationCat) {
        this.notificationCat = notificationCat;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

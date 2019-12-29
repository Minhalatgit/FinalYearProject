package com.minhal.fyp.Model;

public class NotificationList {
    private String title;
    private String description;


    public NotificationList(String title,String description){
        this.title=title;
        this.description=description;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

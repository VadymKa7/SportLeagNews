package com.gray.vadimsyromiatnik.sportleagnews.models;

/**
 * Created by vadimsyromiatnik on 3/3/18.
 */

public class BestEvent {
    private String title;
    private String body;
    private String subtitle;

    public BestEvent() {

    }

    public BestEvent(String title, String subtitle, String body) {
        this.title = title;
        this.subtitle = subtitle;
        this.body = body;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getBody() {
        return body;
    }

    public String getTitle() {
        return title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}

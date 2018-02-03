package com.gray.vadimsyromiatnik.sportleagnews.models;

/**
 * Created by vadimsyromiatnik on 1/20/18.
 */

public  class NewsList {
    private String title;
    private String body;
    private String link;
    private String photo;
    private String subtitle;


    public NewsList() {
    }

    public NewsList(String title, String subtitle, String body, String link, String photo) {
        this.title = title;
        this.subtitle = subtitle;
        this.body = body;
        this.link = link;
        this.photo = photo;
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

    public String getLink() {
        return link;
    }

    public String getPhoto() {
        return photo;
    }

    public String getTitle() {
        return title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

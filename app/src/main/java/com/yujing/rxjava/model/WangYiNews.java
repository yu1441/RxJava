package com.yujing.rxjava.model;

/**
 * 网易新闻
 * https://api.apiopen.top/getWangYiNews
 */
public class WangYiNews {
    String path;
    String image;
    String title;
    String passtime;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPasstime() {
        return passtime;
    }

    public void setPasstime(String passtime) {
        this.passtime = passtime;
    }

    @Override
    public String toString() {
        return "WangYiNews{" +
                "path='" + path + '\'' +
                ", image='" + image + '\'' +
                ", title='" + title + '\'' +
                ", passtime='" + passtime + '\'' +
                '}';
    }
}

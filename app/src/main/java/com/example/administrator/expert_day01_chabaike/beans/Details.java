package com.example.administrator.expert_day01_chabaike.beans;

/**
 * Created by Administrator on 2016/6/22 0022.
 */
public class Details {
    private String title;//资讯标题
    private String time;
    private String img;//图片
    private String keywords;//关键字
    private String message;//资讯内容

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

package com.example.stocknews;

public class NewsModel {
    String urlImg,title;

    public NewsModel(String urlImg, String title) {
        this.urlImg = urlImg;
        this.title = title;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public String getTitle() {
        return title;
    }
}

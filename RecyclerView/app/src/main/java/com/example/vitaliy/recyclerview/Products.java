package com.example.vitaliy.recyclerview;

public class Products {
    private int Id;
    private String title;
    private String desc;

    public Products(int id, String title, String desc) {
        Id = id;
        this.title = title;
        this.desc = desc;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
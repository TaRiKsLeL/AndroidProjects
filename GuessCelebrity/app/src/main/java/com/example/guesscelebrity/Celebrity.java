package com.example.guesscelebrity;

import android.graphics.Bitmap;

public class Celebrity {

    private String name;
    private Bitmap bitmap;

    public Celebrity(){}

    public Celebrity(String name, Bitmap bitmap) {
        this.name = name;
        this.bitmap = bitmap;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}

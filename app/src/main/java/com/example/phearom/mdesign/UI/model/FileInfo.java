package com.example.phearom.mdesign.UI.model;

/**
 * Created by phearom on 12/17/15.
 */
public class FileInfo {
    private String title;
    private int image;

    public FileInfo(String title, int image) {
        this.title = title;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}

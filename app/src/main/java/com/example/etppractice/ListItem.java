package com.example.etppractice;

import android.widget.ImageView;
import android.widget.TextView;

public class ListItem {
    int imagePath;
    String textName;

    public ListItem(int imagePath, String textName) {
        this.imagePath = imagePath;
        this.textName = textName;
    }

    public Integer getImagePath() {
        return imagePath;
    }

    public void setImagePath(Integer imagePath) {
        this.imagePath = imagePath;
    }

    public String getTextName() {
        return textName;
    }

    public void setTextName(String textName) {
        this.textName = textName;
    }
}

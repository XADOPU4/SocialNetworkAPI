package com.xadopu4shelepushka.socialnetwork.models;

import lombok.Data;
import lombok.NonNull;

import java.util.Date;

@Data
public class Post implements Validatable {

    private int id;
    private int userId;
    @NonNull private String text;
    private String imagePath;
    private Date date;

    public Post(int id, int userId, @NonNull String text, String imagePath, Date date) {
        this.id = id;
        this.userId = userId;
        this.text = text;
        this.imagePath = imagePath;
        this.date = date;
    }

    @Override
    public boolean isValid() {
        return !text.isEmpty();
    }
}

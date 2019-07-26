package com.example.miqo93.mykinolistapplication;

import android.graphics.drawable.Drawable;

public class ObjectFilm {
    private String mTitle;
    private Drawable mImage;
    private String mDescription;
    private int mRating;
    private boolean mLike;

    public ObjectFilm() {

    }

    ObjectFilm(String title, Drawable image, String description, int rating, boolean like) {
        this.mTitle = title;
        this.mDescription = description;
        this.mImage = image;
        this.mRating = rating;
        this.mLike = like;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String name) {
        this.mTitle = name;
    }

    public Drawable getImage() {
        return mImage;
    }

    public void setImage(Drawable image) {
        this.mImage = image;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }

    public int getRating() {
        return mRating;
    }

    public void setRating(int rating) {
        this.mRating = rating;
    }

    public boolean getLike() {
        return mLike;
    }

    public void setLike(boolean like) {
        this.mLike = like;
    }
}

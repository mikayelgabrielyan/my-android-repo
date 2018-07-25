package com.example.miqo93.filmlist;

import android.graphics.drawable.Drawable;

public class Film {
    private String mTitle, mDescription, mUrl;
    private int mImage;
    private float mRating;

    public Film() {
    }

    public Film(String mTitle, String mDescription, String mUrl, int mImage) {
        this.mTitle = mTitle;
        this.mDescription = mDescription;
        this.mUrl = mUrl;
        this.mImage = mImage;
    }

    private boolean mLike;

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public int getmImage() {
        return mImage;
    }

    public void setmImage(int mImage) {
        this.mImage = mImage;
    }

    public float getmRating() {
        return mRating;
    }

    public void setmRating(float mRating) {
        this.mRating = mRating;
    }

    public boolean ismLike() {
        return mLike;
    }

    public void setmLike(boolean mLike) {
        this.mLike = mLike;
    }
}
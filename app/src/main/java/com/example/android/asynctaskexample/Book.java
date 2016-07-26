package com.example.android.asynctaskexample;

import java.net.URI;

/**
 * Created by Daniel on 7/24/2016.
 */
public class Book {

    private String mTitle;
    private String mAuthor;
    private int mImgResourceId = HAS_NO_IMAGE;
    private static final int HAS_NO_IMAGE = -1;
    private URI mImgUri = null;

    public Book(String mTitle, String mAuthor, URI mImgUri) {
        this.mTitle = mTitle;
        this.mAuthor = mAuthor;
        this.mImgUri = mImgUri;
    }

    public Book(String mTitle, String mAuthor) {
        this.mTitle = mTitle;
        this.mAuthor = mAuthor;
    }

    public Book(String mTitle, String mAuthor, int mImgResourceId) {
        this.mTitle = mTitle;
        this.mAuthor = mAuthor;
        this.mImgResourceId = mImgResourceId;
    }

    @Override
    public String toString() {
        return "Book{" +
                "mTitle='" + mTitle + '\'' +
                ", mAuthor='" + mAuthor + '\'' +
                '}';
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmAuthor() {
        return mAuthor;
    }

    public void setmAuthor(String mAuthor) {
        this.mAuthor = mAuthor;
    }

    public int getmImgResourceId() {
        return mImgResourceId;
    }

    public void setmImgResourceId(int mImgResourceId) {
        this.mImgResourceId = mImgResourceId;
    }

    public boolean hasImage (){return mImgResourceId != HAS_NO_IMAGE;}
}

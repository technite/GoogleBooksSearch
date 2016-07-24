package com.example.android.asynctaskexample;

/**
 * Created by Daniel on 7/22/2016.
 */
public class User {

    private int mId;
    private String mName;
    private String mEmail;

    public User(int mId, String mName, String mEmail) {
        this.mId = mId;
        this.mName = mName;
        this.mEmail = mEmail;
    }

    @Override
    public String toString() {
        return "User{" +
                "mId=" + mId +
                ", mName='" + mName + '\'' +
                ", mEmail='" + mEmail + '\'' +
                '}';
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }
}

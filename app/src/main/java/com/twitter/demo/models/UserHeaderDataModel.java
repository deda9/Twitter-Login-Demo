package com.twitter.demo.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Bassem Qoulta (Deda) on  7/14/17.
 * Bassem.Qoulta@gmail.com
 * Basem083926@feng.bu.edu.eg
 * +201225361630
 */


public class UserHeaderDataModel implements Parcelable {

    public String userName;
    public String profileUrl;
    public String backgroundUrl;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userName);
        dest.writeString(this.profileUrl);
        dest.writeString(this.backgroundUrl);
    }

    public UserHeaderDataModel() {
    }

    protected UserHeaderDataModel(Parcel in) {
        this.userName = in.readString();
        this.profileUrl = in.readString();
        this.backgroundUrl = in.readString();
    }

    public static final Parcelable.Creator<UserHeaderDataModel> CREATOR = new Parcelable.Creator<UserHeaderDataModel>() {
        @Override
        public UserHeaderDataModel createFromParcel(Parcel source) {
            return new UserHeaderDataModel(source);
        }

        @Override
        public UserHeaderDataModel[] newArray(int size) {
            return new UserHeaderDataModel[size];
        }
    };
}

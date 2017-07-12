package com.twitter.demo.models;

import com.google.gson.annotations.SerializedName;
import com.twitter.sdk.android.core.models.User;

import java.util.List;

/**
 * Created by Bassem Qoulta (Deda) on  7/12/17.
 * Bassem.Qoulta@gmail.com
 * Basem083926@feng.bu.edu.eg
 * +201225361630
 */


public class FollowerListResponse {

    @SerializedName("next_cursor")
    private double nextCursor;
    @SerializedName("next_cursor_str")
    private String nextCursorStr;
    @SerializedName("previous_cursor")
    private int previousCursor;
    @SerializedName("previous_cursor_str")
    private String previousCursorStr;
    @SerializedName("users")
    private List<User> users;

    public void setNextCursor(double nextCursor){
        this.nextCursor = nextCursor;
    }
    public double getNextCursor(){
        return this.nextCursor;
    }
    public void setNextCursorStr(String nextCursorStr){
        this.nextCursorStr = nextCursorStr;
    }
    public String getNextCursorStr(){
        return this.nextCursorStr;
    }
    public void setPreviousCursor(int previousCursor){
        this.previousCursor = previousCursor;
    }
    public int getPreviousCursor(){
        return this.previousCursor;
    }
    public void setPreviousCursorStr(String previousCursorStr){
        this.previousCursorStr = previousCursorStr;
    }
    public String getPreviousCursorStr(){
        return this.previousCursorStr;
    }
    public void setUsers(List<User> users){
        this.users = users;
    }
    public List<User> getUsers(){
        return this.users;
    }

}

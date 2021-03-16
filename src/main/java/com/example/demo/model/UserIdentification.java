package com.example.demo.model;

public class UserIdentification {
    private String cookieID;
    private int userID;

    public UserIdentification(String cookieID, int userID) {
        this.cookieID = cookieID;
        this.userID = userID;
    }

    public String getCookieID() {
        return cookieID;
    }

    public void setCookieID(String cookieID) {
        this.cookieID = cookieID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int UserID) {
        this.userID = userID;
    }

}
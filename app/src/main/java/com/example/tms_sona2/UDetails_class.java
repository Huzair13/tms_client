package com.example.tms_sona2;

public class UDetails_class {
    String uname;
    String pos;
    String userID;

    public UDetails_class() {
    }

    public UDetails_class(String uname, String pos, String userID) {
        this.uname = uname;
        this.pos = pos;
        this.userID = userID;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}

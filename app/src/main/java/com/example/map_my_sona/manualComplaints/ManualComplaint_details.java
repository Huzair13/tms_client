package com.example.map_my_sona.manualComplaints;

import android.text.format.Time;

import java.util.Date;

public class ManualComplaint_details {

    private String com_by_Name;
    private String dep_res;
    private String branch;
    private String location;
    private String object;
    private String com_details;
    private String priority;
    private String status;
    private String intercomNum;
    private String phoneNum;
    private String key;
    private String date;
    private String time;
    private String UID;

    public ManualComplaint_details() {
    }

    public ManualComplaint_details(String com_by_Name, String dep_res, String branch, String location, String object, String com_details, String priority, String status, String intercomNum, String phoneNum, String key, String date, String time, String UID) {
        this.com_by_Name = com_by_Name;
        this.dep_res = dep_res;
        this.branch = branch;
        this.location = location;
        this.object = object;
        this.com_details = com_details;
        this.priority = priority;
        this.status = status;
        this.intercomNum = intercomNum;
        this.phoneNum = phoneNum;
        this.key = key;
        this.date = date;
        this.time = time;
        this.UID = UID;
    }

    public String getCom_by_Name() {
        return com_by_Name;
    }

    public void setCom_by_Name(String com_by_Name) {
        this.com_by_Name = com_by_Name;
    }

    public String getDep_res() {
        return dep_res;
    }

    public void setDep_res(String dep_res) {
        this.dep_res = dep_res;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getCom_details() {
        return com_details;
    }

    public void setCom_details(String com_details) {
        this.com_details = com_details;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIntercomNum() {
        return intercomNum;
    }

    public void setIntercomNum(String intercomNum) {
        this.intercomNum = intercomNum;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }
}
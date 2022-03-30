package com.example.map_my_sona;

public class ManualComplaint_details {
    private String dep_res;
    private String branch;
    private String location;
    private String object;
    private String com_details;
    private String priority;
    private String intercomNum;
    private String phoneNum;

    public ManualComplaint_details() {
    }

    public ManualComplaint_details(String dep_res, String branch, String location, String object, String com_details, String priority, String intercomNum, String phoneNum) {
        this.dep_res = dep_res;
        this.branch = branch;
        this.location = location;
        this.object = object;
        this.com_details = com_details;
        this.priority = priority;
        this.intercomNum = intercomNum;
        this.phoneNum = phoneNum;
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
}

package com.example.map_my_sona;

public class AddNewData {

    private int sn_no;
    private String make;
    private String model;
    private String procurement;
    private String power_rating;
    private String wperiod;
    private String wexpiry;
    private String ins_by;
    private String ins_date;
    private String config;
    private String dep_of_pro;
    private String location;
    private String floor;
    private String bname;

    public AddNewData() {
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public AddNewData(int sn_no, String make, String model, String procurement, String power_rating, String wperiod, String wexpiry, String ins_by, String ins_date, String config, String dep_of_pro, String location, String floor, String bname) {
        this.sn_no = sn_no;
        this.make = make;
        this.model = model;
        this.procurement = procurement;
        this.power_rating = power_rating;
        this.wperiod = wperiod;
        this.wexpiry = wexpiry;
        this.ins_by = ins_by;
        this.ins_date = ins_date;
        this.config = config;
        this.dep_of_pro = dep_of_pro;
        this.location = location;
        this.floor=floor;
        this.bname=bname;
    }

    public int getSn_no() {
        return sn_no;
    }

    public void setSn_no(int sn_no) {
        this.sn_no = sn_no;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getProcurement() {
        return procurement;
    }

    public void setProcurement(String procurement) {
        this.procurement = procurement;
    }

    public String getPower_rating() {
        return power_rating;
    }

    public void setPower_rating(String power_rating) {
        this.power_rating = power_rating;
    }

    public String getWperiod() {
        return wperiod;
    }

    public void setWperiod(String wperiod) {
        this.wperiod = wperiod;
    }

    public String getWexpiry() {
        return wexpiry;
    }

    public void setWexpiry(String wexpiry) {
        this.wexpiry = wexpiry;
    }

    public String getIns_by() {
        return ins_by;
    }

    public void setIns_by(String ins_by) {
        this.ins_by = ins_by;
    }

    public String getIns_date() {
        return ins_date;
    }

    public void setIns_date(String ins_date) {
        this.ins_date = ins_date;
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    public String getDep_of_pro() {
        return dep_of_pro;
    }

    public void setDep_of_pro(String dep_of_pro) {
        this.dep_of_pro = dep_of_pro;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
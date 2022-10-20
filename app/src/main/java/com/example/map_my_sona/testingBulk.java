package com.example.map_my_sona;

public class testingBulk {
    private String uniqueID;
    private String model;
    private String make;
    private String warranty;

    public testingBulk() {

    }

    public testingBulk(String uniqueID, String model, String make, String warranty) {
        this.uniqueID = uniqueID;
        this.model = model;
        this.make = make;
        this.warranty = warranty;
    }

    public String getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(String uniqueID) {
        this.uniqueID = uniqueID;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }
}

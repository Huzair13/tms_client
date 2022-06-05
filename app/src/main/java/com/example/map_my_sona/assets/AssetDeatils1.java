package com.example.map_my_sona.assets;

public class AssetDeatils1 {
    private String Amount;
    private int Bill_No;
    private String DOP;
    private int Quantity;
    private String Supplier_Address;
    private String Supplier_Name;
    private String model;
    private String ref_no;

    public AssetDeatils1(String amount, int bill_No, String DOP, int quantity, String supplier_Address, String supplier_Name, String model, String ref_no) {
        Amount = amount;
        Bill_No = bill_No;
        this.DOP = DOP;
        Quantity = quantity;
        Supplier_Address = supplier_Address;
        Supplier_Name = supplier_Name;
        this.model = model;
        this.ref_no = ref_no;
    }

    public AssetDeatils1() {
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public int getBill_No() {
        return Bill_No;
    }

    public void setBill_No(int bill_No) {
        Bill_No = bill_No;
    }

    public String getDOP() {
        return DOP;
    }

    public void setDOP(String DOP) {
        this.DOP = DOP;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public String getSupplier_Address() {
        return Supplier_Address;
    }

    public void setSupplier_Address(String supplier_Address) {
        Supplier_Address = supplier_Address;
    }

    public String getSupplier_Name() {
        return Supplier_Name;
    }

    public void setSupplier_Name(String supplier_Name) {
        Supplier_Name = supplier_Name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getRef_no() {
        return ref_no;
    }

    public void setRef_no(String ref_no) {
        this.ref_no = ref_no;
    }
}

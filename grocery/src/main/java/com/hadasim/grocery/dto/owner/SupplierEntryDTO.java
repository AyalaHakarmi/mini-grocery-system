
package com.hadasim.grocery.dto.owner;


public class SupplierEntryDTO {

    private String supplierName;
    private String contactName;
    private String phoneNumber;
    private double price;
    private int minOrderQuantity;

    public SupplierEntryDTO() {}

    public SupplierEntryDTO(String supplierName, String contactName, String phone, double price, int minOrderQuantity) {
        this.supplierName = supplierName;
        this.contactName = contactName;
        this.phoneNumber = phone;
        this.price = price;
        this.minOrderQuantity = minOrderQuantity;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phone) {
        this.phoneNumber = phone;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getMinOrderQuantity() {
        return minOrderQuantity;
    }

    public void setMinOrderQuantity(int minOrderQuantity) {
        this.minOrderQuantity = minOrderQuantity;
    }
}
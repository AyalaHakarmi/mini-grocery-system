package com.hadasim.dto;



public class SupplierDTO {
    private String supplierName;
    private String phoneNumber;
private String contactName;
    public SupplierDTO(){}
    public SupplierDTO(String supplierName,String phoneNumber, String contactName){
        this.supplierName= supplierName;
        this.phoneNumber=phoneNumber;
        this.contactName=contactName;
        
    }

    public String getSupplierName() { return supplierName; }
    public void setSupplierName(String supplierName) { this.supplierName = supplierName; }
    
    

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
     public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    
}


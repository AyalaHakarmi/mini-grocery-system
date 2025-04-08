package com.hadasim.grocery.dto;


public class OrderRequestDTO {
    private String supplierName;
    private String productName;
    private int quantity;

    public OrderRequestDTO() {
    }
    public OrderRequestDTO(String supplier,String name,int quantity){
        this.supplierName= supplier;
        this.productName=name;
        this.quantity=quantity;
    }
    public String getSupplierName() {
        return supplierName;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    

    
}


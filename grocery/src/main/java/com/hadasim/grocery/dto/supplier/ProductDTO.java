package com.hadasim.grocery.dto.supplier;

public class ProductDTO {
    private String productName;
    private double price;
    private String supplierName;
    private int minQuantityPurchase;

    public ProductDTO() {
    }

    public ProductDTO(String name, double price, String supplier,int minQuantityPurchase) {
        this.productName = name;
        this.price = price;
        this.supplierName= supplier;
        this.minQuantityPurchase=minQuantityPurchase;
    }
    public int getMinQuantityPurchase() { return minQuantityPurchase; }
    public void setMinQuantityPurchase(int minQuantityPurchase) { this.minQuantityPurchase = minQuantityPurchase; }

    public String getProductName() {
        return productName;
    }

    public void setProcuctName(String name) {
        this.productName = name;
    }
    
     public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplier) {
        this.supplierName = supplier;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}


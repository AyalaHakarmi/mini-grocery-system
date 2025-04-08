package com.hadasim.dto;

public class ProductDTO {
    private String productName;
    private double price;
    private String supplierName;
    private int minQuantityPurchase;

    public ProductDTO() {}

    public String getSupplierName() { return supplierName; }
    public void setSupplierName(String supplierName) { this.supplierName = supplierName; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    
    public int getMinQuantityPurchase() { return minQuantityPurchase; }
    public void setMinQuantityPurchase(int minQuantityPurchase) { this.minQuantityPurchase = minQuantityPurchase; }

}



package com.hadasim.grocery.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Product {

    @Id
    private String productName;

    private int minStockQuantity;
    private int currentQuantity;

    @OneToMany(mappedBy = "product")
    private List<SupplierProduct> supplierProducts;

    public Product() {}
    public Product(String productName,int minStockQuantity,int currentQuantity) {
        this.productName =productName;
        this.minStockQuantity =minStockQuantity;
        this.currentQuantity =currentQuantity;
    }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public int getMinStockQuantity() { return minStockQuantity; }
    public void setMinStockQuantity(int minStockQuantity) { this.minStockQuantity = minStockQuantity; }

    public int getCurrentQuantity() { return currentQuantity; }
    public void setCurrentQuantity(int currentQuantity) { this.currentQuantity = currentQuantity; }

    public List<SupplierProduct> getSupplierProducts() { return supplierProducts; }
    public void setSupplierProducts(List<SupplierProduct> supplierProducts) { this.supplierProducts = supplierProducts; }
}


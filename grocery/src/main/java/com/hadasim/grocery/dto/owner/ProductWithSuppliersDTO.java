
package com.hadasim.grocery.dto.owner;

import java.util.List;

public class ProductWithSuppliersDTO {

    private String productName;
    private int currentQuantity;
    private int minStockQuantity;
    private List<SupplierEntryDTO> suppliers;

    public ProductWithSuppliersDTO() {}

    public ProductWithSuppliersDTO(String name, int currentQuantity, int minStockQuantity, List<SupplierEntryDTO> suppliers) {
        this.productName = name;
        this.currentQuantity = currentQuantity;
        this.minStockQuantity = minStockQuantity;
        this.suppliers = suppliers;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String name) {
        this.productName = name;
    }

    public int getCurrentQuantity() {
        return currentQuantity;
    }

    public void setCurrentQuantity(int currentQuantity) {
        this.currentQuantity = currentQuantity;
    }

    public int getMinStockQuantity() {
        return minStockQuantity;
    }

    public void setMinStockQuantity(int minStockQuantity) {
        this.minStockQuantity = minStockQuantity;
    }

    public List<SupplierEntryDTO> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<SupplierEntryDTO> suppliers) {
        this.suppliers = suppliers;
    }
}

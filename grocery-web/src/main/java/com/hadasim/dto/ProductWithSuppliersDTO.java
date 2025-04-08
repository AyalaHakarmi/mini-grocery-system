
package com.hadasim.dto;

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

    public int getCurrentQuantity() {
        return currentQuantity;
    }

    public int getMinStockQuantity() {
        return minStockQuantity;
    }

    public List<SupplierEntryDTO> getSuppliers() {
        return suppliers;
    }
}

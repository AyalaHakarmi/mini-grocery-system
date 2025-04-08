

package com.hadasim.grocery.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SupplierProductId implements Serializable {

    private String productName;
    private String supplierName;

    public SupplierProductId() {}

    public SupplierProductId(String productName, String supplierName) {
        this.productName = productName;
        this.supplierName = supplierName;
    }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public String getSupplierName() { return supplierName; }
    public void setSupplierName(String supplierName) { this.supplierName = supplierName; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SupplierProductId)) return false;
        SupplierProductId that = (SupplierProductId) o;
        return Objects.equals(productName, that.productName) &&
               Objects.equals(supplierName, that.supplierName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName, supplierName);
    }
}






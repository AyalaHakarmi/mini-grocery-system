
package com.hadasim.grocery.model;

import jakarta.persistence.*;

@Entity
public class SupplierProduct {

    @EmbeddedId
    private SupplierProductId id;

    @ManyToOne
    @MapsId("productName")
    @JoinColumn(name = "product_name")
    private Product product;

    @ManyToOne
    @MapsId("supplierName")
    @JoinColumn(name = "supplier_name")
    private Supplier supplier;

    private double price;
    private int minOrderQuantity;

    public SupplierProduct() {}

    public SupplierProductId getId() { return id; }
    public void setId(SupplierProductId id) { this.id = id; }

    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }

    public Supplier getSupplier() { return supplier; }
    public void setSupplier(Supplier supplier) { this.supplier = supplier; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getMinOrderQuantity() { return minOrderQuantity; }
    public void setMinOrderQuantity(int minOrderQuantity) { this.minOrderQuantity = minOrderQuantity; }
}


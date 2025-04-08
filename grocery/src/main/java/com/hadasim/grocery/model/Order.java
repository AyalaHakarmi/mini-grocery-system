package com.hadasim.grocery.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // The date the order was placed
    private LocalDateTime orderDate;

    // Order status: for example Pending / In-process / Completed
    private String status;
    
    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "product_name", referencedColumnName = "product_name"),
        @JoinColumn(name = "supplier_name", referencedColumnName = "supplier_name")
    })
    private SupplierProduct supplierProduct;
    
    private int quantity;

    // Constructors
    public Order() {}

    public Order( int quantity ,LocalDateTime orderDate, String status, SupplierProduct supplierProduct) {
        this.orderDate = orderDate;
        this.status = status;
        this.supplierProduct=supplierProduct;
        this.quantity= quantity;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

  

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public int getQuantity() {
    return quantity;
}
    public SupplierProduct getSupplierProduct() {
    return supplierProduct;
}


}

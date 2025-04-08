package com.hadasim.grocery.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Supplier {


    @Id
    private String supplierName;
    
    private String phoneNumber;
    private String contactName;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
    @JsonManagedReference // Include products when serializing supplier
    private List<SupplierProduct> products;

    // Constructors, getters, setters...

    public Supplier() {}

    public Supplier(String supplierName, String phoneNumber, String contactName) {
        this.supplierName = supplierName;
        this.phoneNumber = phoneNumber;
        this.contactName = contactName;
    }

    
    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public List<SupplierProduct> getProducts() {
        return products;
    }

    public void setProducts(List<SupplierProduct> products) {
        this.products = products;
    }
}

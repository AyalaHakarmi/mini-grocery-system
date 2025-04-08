package com.hadasim.supplierclient.beans;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.hadasim.dto.ProductDTO;

@Named
@SessionScoped
public class RegistrationSessionBean implements Serializable {


    private String supplierName;
    private String phoneNumber;
    private String contactName;
    private List<ProductDTO> products = new ArrayList<>();



    // Getters and setters
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
    public List<ProductDTO> getProducts() {
        return products;
    }

    // Method to add a product
    public void addProduct(ProductDTO product) {
        products.add(product);
    }

    // Clear all data (optional)
    public void reset() {
        supplierName = null;
        phoneNumber = null;
        contactName = null;
        products.clear();
    }
}


package com.hadasim.groceryownerclient.beans;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hadasim.dto.ProductWithSuppliersDTO;
import com.hadasim.dto.SupplierEntryDTO;
import com.hadasim.dto.OrderRequestDTO;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;


import java.io.OutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Named("createorderBean")
@SessionScoped
public class CreateOrderBean implements Serializable {

    private List<ProductWithSuppliersDTO> products;
    private Double selectedPrice;
    private String selectedProductName;
    private SupplierEntryDTO selectedSupplier;
    private Integer quantity;

    @PostConstruct
    public void init() {
        loadProducts();
    }

    public void loadProducts() {
        try {
            URL url = new URL("http://localhost:9090/api/owner/products/with-suppliers");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() == 200) {
                try (InputStream is = conn.getInputStream()) {
                    ObjectMapper mapper = new ObjectMapper();
                    products = mapper.readValue(is, new TypeReference<List<ProductWithSuppliersDTO>>() {});
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   
    public String selectSupplier(String productName, SupplierEntryDTO supplier) {
    this.selectedProductName = productName;
    this.selectedSupplier = supplier;
    this.selectedPrice = supplier.getPrice(); 
    this.quantity = null;
    return null;
}


    public String submitOrder() {
    try {
        URL url = new URL("http://localhost:9090/api/owner/order");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/json");

        OrderRequestDTO order = new OrderRequestDTO(
            selectedSupplier.getSupplierName(),
            selectedProductName,
            quantity
        );

        ObjectMapper mapper = new ObjectMapper();
        try (OutputStream os = conn.getOutputStream()) {
            mapper.writeValue(os, order);
        }

        int responseCode = conn.getResponseCode();
        if (responseCode == 200) {
            // כל טוב
            try (InputStream is = conn.getInputStream()) {
                boolean result = mapper.readValue(is, Boolean.class);
                if (result) {
                  
                    FacesContext.getCurrentInstance().addMessage(null,
    new FacesMessage(FacesMessage.SEVERITY_INFO, "Order submitted successfully- the overall price is " + (selectedPrice*quantity) , null));

                    loadProducts(); // refresh product stock
                    selectedProductName = null;
                    selectedSupplier = null;
                    selectedPrice=0.0;
                    quantity = null;
                } else {
        
                    FacesContext.getCurrentInstance().addMessage(null,
    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Order failed due to invalid quantity.", null));
                }
            }
        } else if (responseCode == 400) {
            FacesContext.getCurrentInstance().addMessage(null,
    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Quantity is below minimum order requirement", null));

        } else {
            FacesContext.getCurrentInstance().addMessage(null,
    new FacesMessage(FacesMessage.SEVERITY_INFO, "Failed to submit order. HTTP code: " + responseCode, null));
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}


    // Getters and setters

    public List<ProductWithSuppliersDTO> getProducts() {
        return products;
    }

    public String getSelectedProductName() {
        return selectedProductName;
    }

    public SupplierEntryDTO getSelectedSupplier() {
        return selectedSupplier;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
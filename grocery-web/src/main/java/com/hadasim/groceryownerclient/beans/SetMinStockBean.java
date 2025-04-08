package com.hadasim.groceryownerclient.beans;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;

@Named("setMinStockBean")
@SessionScoped
public class SetMinStockBean implements Serializable {

    private String productName;
    private Integer minStockQuantity;

    public String submitMinStock() {
        try {
            if (productName == null || productName.isEmpty() || minStockQuantity == null) {
                FacesContext.getCurrentInstance().addMessage(null,
    new FacesMessage(FacesMessage.SEVERITY_INFO, "Invalid input.", null));
                return null;
            }

            String urlStr = "http://localhost:9090/api/owner/products/" + 
                            productName + "/min-stock/" + minStockQuantity;

            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");

            int responseCode = conn.getResponseCode();
            if (responseCode == 200 || responseCode == 204) {
                FacesContext.getCurrentInstance().addMessage(null,
    new FacesMessage(FacesMessage.SEVERITY_INFO, "Minimum stock updated.", null));

                productName = null;
                minStockQuantity = null;
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
    new FacesMessage(FacesMessage.SEVERITY_INFO, "Failed to update stock. HTTP code: " + responseCode, null));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // Getters and setters
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getMinStockQuantity() {
        return minStockQuantity;
    }

    public void setMinStockQuantity(Integer minStockQuantity) {
        this.minStockQuantity = minStockQuantity;
    }
}

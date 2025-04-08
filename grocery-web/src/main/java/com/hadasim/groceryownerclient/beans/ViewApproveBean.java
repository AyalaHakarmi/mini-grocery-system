package com.hadasim.groceryownerclient.beans;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hadasim.dto.OrderDTO;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;

import jakarta.inject.Named;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import java.io.InputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

@Named("viewApproveBean")
@ViewScoped
public class ViewApproveBean implements Serializable {

    private List<OrderDTO> pendingOrders;
    private List<OrderDTO> allOrders;

    @PostConstruct
    public void init() {
        loadPendingOrders();
        loadAllOrders();
    }

    public void loadPendingOrders() {
        try {
            URL url = new URL("http://localhost:9090/api/owner/orders/pending-or-in-process");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() == 200) {
                try (InputStream is = conn.getInputStream()) {
                    ObjectMapper mapper = new ObjectMapper();
                    OrderDTO[] orders = mapper.readValue(is, OrderDTO[].class);
                    this.pendingOrders = Arrays.asList(orders);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadAllOrders() {
        try {
            URL url = new URL("http://localhost:9090/api/owner/orders/all");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() == 200) {
                try (InputStream is = conn.getInputStream()) {
                    ObjectMapper mapper = new ObjectMapper();
                    OrderDTO[] orders = mapper.readValue(is, OrderDTO[].class);
                    this.allOrders = Arrays.asList(orders);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<OrderDTO> getAllOrders() {
    if (allOrders == null) {
        loadAllOrders();
    }
    return allOrders;
}


public String completeOrder(Long id) {
    try {
        URL url = new URL("http://localhost:9090/api/owner/orders/" + id + "/complete");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");

        int responseCode = conn.getResponseCode();
        if (responseCode == 200) {
            loadPendingOrders();
            loadAllOrders();
            return "viewApproveOrders.xhtml?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
    new FacesMessage(FacesMessage.SEVERITY_INFO, "Failed to approve order. HTTP code: " + responseCode, null));

        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}



    // Getters and setters

    public List<OrderDTO> getPendingOrders() {
        return pendingOrders;
    }

}

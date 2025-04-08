package com.hadasim.supplierclient.beans;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hadasim.dto.OrderDTO;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

import java.io.InputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;


@Named("orderManagementBean")
@SessionScoped
public class OrderManagementBean implements Serializable {

    private List<OrderDTO> orders;

    @Inject
    private RegistrationSessionBean session;

    @PostConstruct
    public void init() {
        loadOrders();
    }

    public void loadOrders() {
        try {

            URL url = new URL("http://localhost:9090/api/suppliers/" + session.getSupplierName()+ "/orders");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() == 200) {
                ObjectMapper mapper = new ObjectMapper();
                try (InputStream is = conn.getInputStream()) {
                    OrderDTO[] orderArray = mapper.readValue(is, OrderDTO[].class);
                    this.orders = Arrays.asList(orderArray);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<OrderDTO> getOrders() {
        loadOrders(); 
        return orders;
    }
    
    public String approveOrder(Long orderId) {
    try {
        URL url = new URL("http://localhost:9090/api/suppliers/orders/" + orderId + "/approve");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("PUT");

        int responseCode = conn.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            loadOrders(); // רענון הרשימה
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
    new FacesMessage(FacesMessage.SEVERITY_INFO, "Failed to approve order: " + responseCode, null));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}

}

package com.hadasim.supplierclient.beans;

import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import com.hadasim.dto.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.inject.Inject;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;



@Named
@RequestScoped
public class RegisterBean {

    @Inject
    private RegistrationSessionBean registrationSessionBean;

    private String supplierName;
    private String phoneNumber;
    private String contactName;
    
  public String submitRegistration() {
    try {
        SupplierForRegisterDTO dto = new SupplierForRegisterDTO();
        dto.setSupplierName(registrationSessionBean.getSupplierName());
        dto.setPhoneNumber(registrationSessionBean.getPhoneNumber());
        dto.setContactName(registrationSessionBean.getContactName());
        dto.setProducts(registrationSessionBean.getProducts());

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(dto);

        URL url = new URL("http://localhost:9090/api/suppliers/register");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);

        try (OutputStream os = con.getOutputStream()) {
            byte[] input = json.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int responseCode = con.getResponseCode();
        if (responseCode == 200) {
            try (InputStream is = con.getInputStream()) {
                Scanner scanner = new Scanner(is).useDelimiter("\\A");
                String response = scanner.hasNext() ? scanner.next() : "";

                if (Boolean.parseBoolean(response)) {
                    FacesContext.getCurrentInstance().addMessage(null,
    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registration success!", null));

                    registrationSessionBean.reset();
                    return "success.xhtml?faces-redirect=true";
                }
            }
        }
        FacesContext.getCurrentInstance().addMessage(null,
    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registration failed. Code: " + responseCode, null));


    } catch (Exception e) {
        e.printStackTrace();
    }

    FacesContext.getCurrentInstance().addMessage(null,
        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Registration failed", "Please check input or try again later"));
    return null;
}




    public String register() {
        // שמירת פרטים זמנית ב־session
        registrationSessionBean.setSupplierName(supplierName);
        registrationSessionBean.setPhoneNumber(phoneNumber);
        registrationSessionBean.setContactName(contactName);

        // מעבר לדף הוספת מוצרים
        return "addProducts.xhtml?faces-redirect=true";
    }

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
}

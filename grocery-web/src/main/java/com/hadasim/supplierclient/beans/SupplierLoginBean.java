package com.hadasim.supplierclient.beans;


import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.*;
import java.io.InputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import java.net.URLEncoder;



@Named("supplierLoginBean")
@SessionScoped
public class SupplierLoginBean implements Serializable {

    private String supplierName;

    @Inject
    private RegistrationSessionBean session;

public String login() {
    try {
        String encodedSupplierName = URLEncoder.encode(supplierName, "UTF-8");
        URL url = new URL("http://localhost:9090/api/suppliers/login?supplierName=" + encodedSupplierName);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        if (conn.getResponseCode() == 200) {
            try (InputStream is = conn.getInputStream()) {
                Scanner scanner = new Scanner(is).useDelimiter("\\A");
                String response = scanner.hasNext() ? scanner.next() : "";

                if (Boolean.parseBoolean(response)) {
                    session.setSupplierName(supplierName); // set session if login succeeds
                    return "supplierOrders.xhtml?faces-redirect=true";
                }
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    FacesContext.getCurrentInstance().addMessage(null,
        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login failed", "Company name not found"));
    return null;
}



    public String getsupplierName() { return supplierName; }
    public void setSupplierName(String supplierName) { this.supplierName = supplierName; }
}

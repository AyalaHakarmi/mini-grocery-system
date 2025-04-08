package com.hadasim.groceryownerclient.beans;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import java.io.Serializable;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;

@Named("ownerLoginBean")
@SessionScoped
public class OwnerLoginBean implements Serializable {

    private String password;
    private static final String CORRECT_PASSWORD = "grocery123";

    public String login() {
        if (CORRECT_PASSWORD.equals(password)) {
            return "/owner/ownerDashboard.xhtml?faces-redirect=true";
        } 
        else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Incorrect password", null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


package com.hadasim.supplierclient.beans;

import com.hadasim.dto.ProductDTO;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.inject.Inject;
import java.io.Serializable;

@Named("productAddBean")
@SessionScoped
public class ProductAddBean implements Serializable {

    private ProductDTO product = new ProductDTO(); 
    
    @Inject
    private RegistrationSessionBean registrationSessionBean;

    public String addProduct() {
        ProductDTO copy = new ProductDTO();
        copy.setProductName(product.getProductName());
        copy.setPrice(product.getPrice());
        copy.setSupplierName(registrationSessionBean.getSupplierName());
        copy.setMinQuantityPurchase(product.getMinQuantityPurchase());
        registrationSessionBean.addProduct(copy); 

        product = new ProductDTO();
        return null;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

}

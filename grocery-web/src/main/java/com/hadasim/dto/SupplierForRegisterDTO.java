
package com.hadasim.dto;

import java.util.List;


public class SupplierForRegisterDTO extends SupplierDTO {
    private List<ProductDTO> products;
    public List<ProductDTO> getProducts() { return products; }
    public void setProducts(List<ProductDTO> products) { this.products = products; }
}

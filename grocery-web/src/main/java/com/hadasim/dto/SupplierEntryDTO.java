/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hadasim.dto;


public class SupplierEntryDTO extends SupplierDTO {
    private double price;
    private int minOrderQuantity;
      public SupplierEntryDTO() {}
    public SupplierEntryDTO(String supplierName, String contactName, String phone, double price, int minOrderQuantity) {
        super(supplierName,phone,contactName);
        this.price = price;
        this.minOrderQuantity = minOrderQuantity;
    }


    public double getPrice() {
        return price;
    }

    public int getMinOrderQuantity() {
        return minOrderQuantity;
    }
}

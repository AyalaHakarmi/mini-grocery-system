/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hadasim.grocery.dto;



public class OrderViewDTO extends OrderRequestDTO {
    private Long id;
    private String status;
    private String orderDate;
    
    public OrderViewDTO(){}
    public OrderViewDTO(Long id,String status,String date,String supplier, String name,int quantity){
        super(supplier,name,quantity);
        this.id = id;
        this.status = status;
        this.orderDate = date;
    }
    
    public Long getId() {
    return id;
}

public String getStatus() {
    return status;
}

public String getOrderDate() {
    return orderDate;
}
}

package com.hadasim.dto;

import java.util.List;

public class OrderDTO extends OrderRequestDTO{
    private Long id;
    private String status;
    private String orderDate;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String date) {
        this.orderDate = date;
    }

   
}


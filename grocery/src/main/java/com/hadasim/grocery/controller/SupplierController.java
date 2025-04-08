package com.hadasim.grocery.controller;

import com.hadasim.grocery.dto.OrderViewDTO;
import com.hadasim.grocery.dto.supplier.*;
import com.hadasim.grocery.dto.owner.*;
import com.hadasim.grocery.model.*;
import com.hadasim.grocery.repository.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private OrderRepository orderRepository;

    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private SupplierProductRepository supplierProductRepository;

    // Register new supplier with product list
   @PostMapping("/register")
public boolean registerSupplier(@RequestBody SupplierDTO supplierDTO) {
    try {
        Supplier supplier = new Supplier();
        supplier.setSupplierName(supplierDTO.getSupplierName());
        supplier.setPhoneNumber(supplierDTO.getPhoneNumber());
        supplier.setContactName(supplierDTO.getContactName());

        supplier = supplierRepository.save(supplier);

        for (ProductDTO productDTO : supplierDTO.getProducts()) {
            Product product = productRepository.findByProductName(productDTO.getProductName());
            if (product == null) {
                product = new Product(productDTO.getProductName(), 0, 0);
                productRepository.save(product);
            }

            SupplierProductId id = new SupplierProductId(product.getProductName(), supplier.getSupplierName());
            SupplierProduct sp = new SupplierProduct();
            sp.setId(id);
            sp.setProduct(product);
            sp.setSupplier(supplier);
            sp.setPrice(productDTO.getPrice());
            sp.setMinOrderQuantity(productDTO.getMinQuantityPurchase());

            supplierProductRepository.save(sp);
        }

        return true;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}

    

   @GetMapping("/login")
public boolean loginSupplier(@RequestParam(name = "supplierName") String supplierName) {
    return supplierRepository.findBySupplierName(supplierName)!= null;
}

    


    @GetMapping("/{supplier}/orders")
public List<OrderViewDTO> getOrdersBySupplier(@PathVariable("supplier") String supplier) {
    return orderRepository.findOrdersBySupplierName(supplier).stream()
        .map(order -> new OrderViewDTO(
            order.getId(),
            order.getStatus(),
            order.getOrderDate().toString(),
            order.getSupplierProduct().getSupplier().getSupplierName(),
            order.getSupplierProduct().getProduct().getProductName(),            
            order.getQuantity()
        ))
        .collect(Collectors.toList());
    
}


    // Approve an order (set status to "In-process")
    @PutMapping("/orders/{orderId}/approve")
    public void approveOrder(@PathVariable("orderId") Long orderId) {
        Optional<Order> optional = orderRepository.findById(orderId);
        if (optional.isPresent()) {
            Order order = optional.get();
            order.setStatus("In-process");
            orderRepository.save(order);
        }
        else{
            throw new IllegalArgumentException("Order not found");
        }
    }
}


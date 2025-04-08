
package com.hadasim.grocery.controller;
import com.hadasim.grocery.dto.OrderViewDTO;
import com.hadasim.grocery.dto.OrderRequestDTO;
import org.springframework.http.ResponseEntity;

import com.hadasim.grocery.dto.owner.*;
import com.hadasim.grocery.model.*;
import com.hadasim.grocery.repository.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.util.Optional;



@RestController
@RequestMapping("/api/owner") 
public class OwnerController {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SupplierProductRepository supplierProductRepository;
    
    @GetMapping("/orders/pending-or-in-process")
public List<OrderViewDTO> pendingOrders() {
    List<Order> orders = orderRepository.findByStatuses(List.of("Pending", "In-process"));
    return orders.stream()
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

    
    @PostMapping("/order")
public ResponseEntity<Boolean> makeOrder(@RequestBody OrderRequestDTO newOrderDTO) {
    SupplierProductId id = new SupplierProductId(
        newOrderDTO.getProductName(), newOrderDTO.getSupplierName());

    SupplierProduct sp = supplierProductRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Supplier product not found"));

    // בדיקת מינימום
    if (newOrderDTO.getQuantity() < sp.getMinOrderQuantity()) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false); 
    }

    Order newOrder = new Order(
        newOrderDTO.getQuantity(), LocalDateTime.now(), "Pending", sp);
    orderRepository.save(newOrder);

    return ResponseEntity.ok(true); // הצלחה
}

    
    @GetMapping("/products/with-suppliers")
    public List<ProductWithSuppliersDTO> getProductsWithSuppliers() {
    List<Product> products = productRepository.findAll();

    return products.stream().map(product -> {
        List<SupplierEntryDTO> supplierEntries = product.getSupplierProducts().stream()
            .map(sp -> {
                Supplier supplier = sp.getSupplier();
                return new SupplierEntryDTO(
                    supplier.getSupplierName(),
                    supplier.getContactName(),
                    supplier.getPhoneNumber(),
                    sp.getPrice(),
                    sp.getMinOrderQuantity()
                );
            })
            .collect(Collectors.toList());

        return new ProductWithSuppliersDTO(
            product.getProductName(),
            product.getCurrentQuantity(),
            product.getMinStockQuantity(),
            supplierEntries
        );
    }).collect(Collectors.toList());
    }
    
    
@PostMapping("/orders/{orderId}/complete")
public ResponseEntity<String> completeOrder(@PathVariable("orderId") Long orderId) {
    Optional<Order> optionalOrder = orderRepository.findById(orderId);
    if (optionalOrder.isEmpty()) {
        return ResponseEntity.notFound().build();
    }

    Order order = optionalOrder.get();

    // Update order status
    order.setStatus("Completed");
    orderRepository.save(order);

    // Update product stock in inventory
    SupplierProduct supplierProduct = order.getSupplierProduct();
    Product product = supplierProduct.getProduct();

    int newQuantity = product.getCurrentQuantity() + order.getQuantity();
    product.setCurrentQuantity(newQuantity);
    productRepository.save(product);

    return ResponseEntity.ok("Order approved and stock updated.");
}



    
       @GetMapping("/orders/all")
    public List<OrderViewDTO> getAllOrders() {
        return orderRepository.findAll()
            .stream()
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
    
    
@PostMapping("/products/{productName}/min-stock/{quantity}")
public ResponseEntity<Void> updateMinStockQuantity(
        @PathVariable(name = "productName") String productName,
        @PathVariable(name = "quantity") int quantity) {

    Product product = productRepository.findByProductName(productName);
    if (product == null) {
        return ResponseEntity.notFound().build();
    }

    product.setMinStockQuantity(quantity);
    productRepository.save(product);

    return ResponseEntity.ok().build();
}

}





    


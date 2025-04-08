package com.hadasim.grocery.controller;


import org.springframework.http.ResponseEntity;

import com.hadasim.grocery.model.*;

import com.hadasim.grocery.repository.*;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SupplierProductRepository supplierProductRepository;

    @Autowired
    private OrderRepository orderRepository;


@PostMapping("/report")
public ResponseEntity<String> updateStockAndHandleOrders(@RequestBody Map<String, Integer> soldItems) {
    StringBuilder result = new StringBuilder();

    if (soldItems == null || soldItems.isEmpty()) {
        return ResponseEntity.badRequest().body("No sold items provided.");
    }

    for (Map.Entry<String, Integer> entry : soldItems.entrySet()) {
        String productName = entry.getKey();
        int quantitySold = entry.getValue();

        Product product = productRepository.findByProductName(productName);
        if (product == null) {
            result.append("❌ Product not found: ").append(productName).append("\n\n");
            continue;
        }

        if (product.getCurrentQuantity() < product.getMinStockQuantity()) {
            result.append("⚠️ Product ").append(product.getProductName())
                  .append(" already below minimum stock. Sale not allowed.\n\n");
            continue;
        }

        int newStock = product.getCurrentQuantity() - quantitySold;

        if (newStock < 0) {
            result.append("❌ Insufficient stock to sell ").append(quantitySold)
                  .append(" units of ").append(product.getProductName())
                  .append(". Available: ").append(product.getCurrentQuantity())
                  .append("\n\n");
            continue;
        }

        if (newStock < product.getMinStockQuantity()) {
            Optional<SupplierProduct> cheapest = supplierProductRepository.findByProductName(productName)
                .stream()
                .min(Comparator.comparing(SupplierProduct::getPrice));

            if (cheapest.isPresent()) {
                SupplierProduct sp = cheapest.get();
                int newOrderQuantity = Math.max(product.getMinStockQuantity(), sp.getMinOrderQuantity());

                Order order = new Order(newOrderQuantity, LocalDateTime.now(), "Pending", sp);
                orderRepository.save(order);

                result.append("✅ Auto-order placed for ").append(newOrderQuantity)
                      .append(" of ").append(product.getProductName())
                      .append(" from ").append(sp.getSupplier().getSupplierName()).append("\n\n");
            } else {
                result.append("❌ No supplier found for ").append(product.getProductName()).append("\n\n");
            }
        }
        else{
            product.setCurrentQuantity(newStock);
            productRepository.save(product);

        result.append("✅ Stock updated for ").append(product.getProductName())
              .append(". New quantity: ").append(newStock).append("\n\n");
        }

        
    }

    return ResponseEntity.ok(result.toString());
}

   

}

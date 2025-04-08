package com.hadasim.grocery.repository;

import com.hadasim.grocery.model.*;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Long> {

        Product findByProductName(String productName);


}

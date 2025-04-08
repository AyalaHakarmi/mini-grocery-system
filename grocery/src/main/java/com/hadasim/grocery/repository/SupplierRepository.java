package com.hadasim.grocery.repository;

import com.hadasim.grocery.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    Supplier findBySupplierName(String supplierName);
    boolean existsBySupplierName(String supplierName);
}


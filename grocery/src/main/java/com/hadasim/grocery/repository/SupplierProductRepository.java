
package com.hadasim.grocery.repository;

import java.util.Optional;
import com.hadasim.grocery.model.SupplierProductId;
import com.hadasim.grocery.model.SupplierProduct;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierProductRepository extends JpaRepository<SupplierProduct, SupplierProductId> {
    @Override
    Optional<SupplierProduct> findById(SupplierProductId id);
    @Query("SELECT sp FROM SupplierProduct sp WHERE sp.product.productName = :name")
    Optional<SupplierProduct> findByProductName(@Param("name") String name);

}

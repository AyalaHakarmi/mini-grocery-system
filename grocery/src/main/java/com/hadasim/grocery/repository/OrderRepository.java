package com.hadasim.grocery.repository;

import com.hadasim.grocery.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o WHERE o.status IN :statuses")
    List<Order> findByStatuses(@Param("statuses") List<String> statuses);

    @Query("SELECT o FROM Order o WHERE o.supplierProduct.supplier.supplierName = :name")
    List<Order> findOrdersBySupplierName(@Param("name") String supplierName);


}




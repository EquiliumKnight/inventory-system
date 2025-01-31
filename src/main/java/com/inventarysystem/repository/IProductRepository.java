package com.inventarysystem.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.inventarysystem.models.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Optional;

public interface IProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findBySku(String sku);

    boolean existsBySku(String sku);

    @Query("select p from Product p left join p.inventories i" +
            "where (:category is null or p.category = :category) and" +
            "(:min is null or p.price >= :min) and" +
            "(:max is null or p.price <= :max) and" +
            "(:minStock is null or i.quantity >= :minStock)")
    Page<Product> findProductByFilters(
            @Param("category") String category,
            @Param("min") BigDecimal min,
            @Param("max") BigDecimal max,
            @Param("minStock") Integer minStock,
            Pageable pageable);


}

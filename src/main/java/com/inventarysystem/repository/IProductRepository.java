package com.inventarysystem.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.inventarysystem.models.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.util.Optional;

public interface IProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findBySku(String sku);

    boolean existsBySku(String sku);

    @Query("SELECT p FROM Product p LEFT JOIN p.inventories i WHERE (:category IS NULL OR p.category = :category) AND (:minPrice IS NULL OR p.price >= :minPrice) AND (:maxPrice IS NULL OR p.price <= :maxPrice) AND (:minStock IS NULL OR i.quantity >= :minStock)")
    Page<Product> findByFilters(
            @Param("category") String category,
            @Param("minPrice") BigDecimal minPrice,
            @Param("maxPrice") BigDecimal maxPrice,
            @Param("minStock") Integer minStock,
            Pageable pageable);
}

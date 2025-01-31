package com.inventarysystem.service;

import com.inventarysystem.models.filter.ProductFilter;
import org.springframework.data.domain.Page;

import com.inventarysystem.models.entity.Product;
import com.inventarysystem.models.request.ProductRequest;

import java.math.BigDecimal;

public interface IProductService {

    Page<Product> paginationProducts(int page, int size, ProductFilter filter);

    Product storeProduct(ProductRequest request);

    Product updateProductById(Long id, ProductRequest request);

    Product findProductById(Long id);

    boolean deleteProductById(Long id);

}

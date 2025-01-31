package com.inventarysystem.service.impl;

import com.inventarysystem.exceptions.ResourceNotFoundException;
import com.inventarysystem.models.filter.ProductFilter;
import com.inventarysystem.utils.PageableUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventarysystem.models.entity.Product;
import com.inventarysystem.models.request.ProductRequest;
import com.inventarysystem.repository.IProductRepository;
import com.inventarysystem.service.IProductService;

@Service
public class ProductServiceImpl implements IProductService {

    private final IProductRepository repository;

    public ProductServiceImpl(IProductRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public Product storeProduct(ProductRequest request) {
        if(repository.existsBySku(request.getSku())) {
            throw new RuntimeException("El sku ingresado ya existe");
        }

        Product newProduct = Product.builder()
            .name(request.getName())
            .category(request.getCategory())
            .description(request.getDescription())
            .price(request.getPrice())
            .sku(request.getSku())
            .build();
        
        return repository.save(newProduct);
    }

    @Override
    public Product updateProductById(Long id, ProductRequest request) {
        Product product = findProductById(id);
        product.setCategory(request.getCategory());
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setDescription(request.getDescription());
        product.setSku(request.getSku());

        return repository.save(product);
    }

    @Override
    public Product findProductById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));
    }

    @Override
    public boolean deleteProductById(Long id) {
        Product product = findProductById(id);

        if(product != null) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Page<Product> paginationProducts(int page, int size, ProductFilter filter) {
        return repository.findByFilters(filter.getCategory(), filter.getMin(), filter.getMax(), filter.getMinStock(), PageableUtil.getPageable(page, size));
    }
}

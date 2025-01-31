package com.inventarysystem.service.impl;

import com.inventarysystem.exceptions.ResourceNotFoundException;
import com.inventarysystem.models.entity.Product;
import com.inventarysystem.repository.IProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    IProductRepository repository;

    @InjectMocks
    ProductServiceImpl service;

    Product product = new Product(1L, "name", "description", "category", new BigDecimal(100), "sku", List.of());

    @Test
    void findProductById() {
        when(repository.findById(any())).thenReturn(Optional.of(product));

        Product result = service.findProductById(1L);
        assertThat(result).isNotNull();
    }

    @Test
    void findProductByIdWithError() {
        when(repository.findById(any())).thenThrow(new ResourceNotFoundException(""));
        assertThrows(ResourceNotFoundException.class, () -> service.findProductById(1L));
    }
}
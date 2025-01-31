package com.inventarysystem.controller;

import com.inventarysystem.models.ResponseGeneric;
import com.inventarysystem.models.entity.Product;
import com.inventarysystem.models.filter.ProductFilter;
import com.inventarysystem.models.request.ProductRequest;
import com.inventarysystem.service.IProductService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final IProductService service;

    public ProductController(IProductService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<ResponseGeneric<Page<Product>>> paginationProducts(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10")Integer size,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(required = false) Integer minStock
    ) {
        ProductFilter filters = ProductFilter.builder()
                .category(category)
                .min(minPrice)
                .max(maxPrice)
                .minStock(minStock)
                .build();
        ResponseGeneric<Page<Product>> res = new ResponseGeneric<>(HttpStatus.OK, "Paginación de productos", service.paginationProducts(page, size, filters));
        return ResponseEntity.ok(res);
    }

    @PostMapping
    public ResponseEntity<ResponseGeneric<Product>> registerProduct(@RequestBody ProductRequest request) {
        ResponseGeneric<Product> res = new ResponseGeneric<>(HttpStatus.OK, "Producto creado", service.storeProduct(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseGeneric<Product>> findProductById(@PathVariable Long id) {
        ResponseGeneric<Product> res = new ResponseGeneric<>(HttpStatus.OK, "Producto encontrado", service.findProductById(id));
        return ResponseEntity.ok(res);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseGeneric<Product>> updateProduct(@PathVariable Long id, @RequestBody ProductRequest request) {
        ResponseGeneric<Product> res = new ResponseGeneric<>(HttpStatus.OK, "Producto actualizado", service.updateProductById(id, request));
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseGeneric<Boolean>> deleteProductById(@PathVariable Long id) {
        ResponseGeneric<Boolean> res = new ResponseGeneric<>(HttpStatus.OK, "Producto eliminado", service.deleteProductById(id));
        return ResponseEntity.ok(res);
    }

}

package com.inventarysystem.controller;

import com.inventarysystem.models.ResponseGeneric;
import com.inventarysystem.models.entity.Product;
import com.inventarysystem.models.filter.ProductFilter;
import com.inventarysystem.models.request.ProductRequest;
import com.inventarysystem.service.IProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Tag(name = "Productos", description = "Operaciones relacionadas con los productos")
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final IProductService service;

    public ProductController(IProductService service) {
        this.service = service;
    }

    @Operation(summary = "Paginación de los productos", description = "Obtiene un paginado de los productos existentes en el sistema")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Paginación de usuarios"),
            @ApiResponse(responseCode = "500", description = "Hubo un problema al realizar la operación"),
    })
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

    @Operation(summary = "Registrar un producto", description = "Registra un producto nuevo en el sistema, verificando si el sku ingresado exista o no")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Producto registrado"),
            @ApiResponse(responseCode = "500", description = "Hubo un problema al realizar la operación"),
    })
    @PostMapping
    public ResponseEntity<ResponseGeneric<Product>> registerProduct(@RequestBody ProductRequest request) {
        ResponseGeneric<Product> res = new ResponseGeneric<>(HttpStatus.OK, "Producto registrado", service.storeProduct(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @Operation(summary = "Obtener un producto por ID", description = "Busca un producto por su ID registrado en el sistema")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto encontrado"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado"),
            @ApiResponse(responseCode = "500", description = "Hubo un problema al realizar la operación"),
    })
    @GetMapping("/{id}")
    public ResponseEntity<ResponseGeneric<Product>> findProductById(@PathVariable Long id) {
        ResponseGeneric<Product> res = new ResponseGeneric<>(HttpStatus.OK, "Producto encontrado", service.findProductById(id));
        return ResponseEntity.ok(res);
    }

    @Operation(summary = "Actualizar producto", description = "Busca un producto por su ID registrado en el sistema, actualizando la información")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto actualizado"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado"),
            @ApiResponse(responseCode = "500", description = "Hubo un problema al realizar la operación"),
    })
    @PutMapping("/{id}")
    public ResponseEntity<ResponseGeneric<Product>> updateProduct(@PathVariable Long id, @RequestBody ProductRequest request) {
        ResponseGeneric<Product> res = new ResponseGeneric<>(HttpStatus.OK, "Producto actualizado", service.updateProductById(id, request));
        return ResponseEntity.ok(res);
    }

    @Operation(summary = "Eliminar producto producto", description = "Busca un producto por su ID registrado eliminandolo en el sistema")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto eliminado"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado"),
            @ApiResponse(responseCode = "500", description = "Hubo un problema al realizar la operación"),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseGeneric<Boolean>> deleteProductById(@PathVariable Long id) {
        ResponseGeneric<Boolean> res = new ResponseGeneric<>(HttpStatus.OK, "Producto eliminado", service.deleteProductById(id));
        return ResponseEntity.ok(res);
    }

}

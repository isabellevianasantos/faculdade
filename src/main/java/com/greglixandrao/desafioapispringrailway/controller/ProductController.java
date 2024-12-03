package com.greglixandrao.desafioapispringrailway.controller;

import com.greglixandrao.desafioapispringrailway.controller.dto.ProductDto;
import com.greglixandrao.desafioapispringrailway.domain.model.Product;
import com.greglixandrao.desafioapispringrailway.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/products")
@Tag(name = "Product Controller", description = "REST API for manipulate products.")
public record ProductController(ProductService productService) {

    @GetMapping
    @Operation(summary = "List all products", description = "List all products in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation successful")
    })
    public ResponseEntity<List<ProductDto>> findAll() {
        var products = productService.findAll();
        var productsDto = products.stream().map(ProductDto::new).toList();
        return ResponseEntity.ok(productsDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a product by ID", description = "Retrieve a specific product by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation successful"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    public ResponseEntity<ProductDto> findById(@PathVariable Long id) {
        var product = productService.findById(id);
        return ResponseEntity.ok(new ProductDto(product));
    }

    @PostMapping
    @Operation(summary = "Create a product", description = "Create a new product and return the created product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Product created successfully"),
            @ApiResponse(responseCode = "422", description = "Product already exists")
    })
    public ResponseEntity<ProductDto> create(@RequestBody ProductDto productDto) {
        var product = productService.create(productDto.toModel());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(product.getId())
                .toUri();
        return ResponseEntity.created(location).body(productDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a product", description = "Update the data of an existing product based by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product updated successfully"),
            @ApiResponse(responseCode = "404", description = "Product not found"),
            @ApiResponse(responseCode = "422", description = "Invalid product data provided")
    })
    public ResponseEntity<ProductDto> update(@PathVariable Long id, @RequestBody ProductDto productDto) {
        var product = productService.update(id, productDto.toModel());
        return ResponseEntity.ok(new ProductDto(product));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a product", description = "Delete a product based on ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Product deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

package com.greglixandrao.desafioapispringrailway.controller.dto;

import com.greglixandrao.desafioapispringrailway.domain.model.Product;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

public record ProductDto(
        Long id,
        String productName,
        String productDescription,
        Double price,
        Integer quantity,
        List<CategoryDto> category) {

    public ProductDto(Product model) {
        this(
                model.getId(),
                model.getProductName(),
                model.getDescription(),
                model.getPrice(),
                model.getStock(),
                ofNullable(model.getCategories()).orElse(emptyList()).stream().map(CategoryDto::new).collect(toList())
        );
    }

    public Product toModel() {
        Product model = new Product();
        model.setId(this.id);
        model.setProductName(this.productName);
        model.setDescription(this.productDescription);
        model.setPrice(this.price);
        model.setStock(this.quantity);
        model.setCategories(ofNullable(this.category).orElse(emptyList()).stream().map(CategoryDto::toModel).collect(toList()));
        return model;
    }

}

package com.greglixandrao.desafioapispringrailway.controller.dto;

import com.greglixandrao.desafioapispringrailway.domain.model.Category;

public record CategoryDto(Long id, String categoryName, String description) {

    public CategoryDto(Category model) {
        this(model.getId(), model.getName(), model.getDescription());
    }

    public Category toModel() {
        Category model = new Category();
        model.setId(this.id);
        model.setName(this.categoryName);
        model.setDescription(this.description);
        return model;
    }
}

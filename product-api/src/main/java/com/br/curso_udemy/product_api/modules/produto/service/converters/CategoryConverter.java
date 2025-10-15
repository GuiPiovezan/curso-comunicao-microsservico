package com.br.curso_udemy.product_api.modules.produto.service.converters;

import com.br.curso_udemy.product_api.modules.produto.model.dto.CategoryRequestDto;
import com.br.curso_udemy.product_api.modules.produto.model.dto.CategoryResponseDto;
import com.br.curso_udemy.product_api.modules.produto.model.entities.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter {

    public CategoryResponseDto toDto(Category category) {
        CategoryResponseDto dto = new CategoryResponseDto();
        dto.setId(category.getId());
        dto.setDescription(category.getDescription());
        return dto;
    }

    public Category toEntity(CategoryRequestDto dto) {
        Category category = new Category();
        category.setDescription(dto.getDescription());
        return category;
    }
}

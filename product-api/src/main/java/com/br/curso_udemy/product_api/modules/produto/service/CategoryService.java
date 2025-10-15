package com.br.curso_udemy.product_api.modules.produto.service;

import com.br.curso_udemy.product_api.config.exceptions.BusinessException;
import com.br.curso_udemy.product_api.modules.produto.model.dto.CategoryRequestDto;
import com.br.curso_udemy.product_api.modules.produto.model.dto.CategoryResponseDto;
import com.br.curso_udemy.product_api.modules.produto.model.entities.Category;
import com.br.curso_udemy.product_api.modules.produto.repository.CategoryRepository;
import com.br.curso_udemy.product_api.modules.produto.service.converters.CategoryConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryConverter categoryConverter;

    public CategoryResponseDto save(CategoryRequestDto request) {
        validateCategoryDescription(request);
        Category category = categoryConverter.toEntity(request);
        category = categoryRepository.save(category);
        return categoryConverter.toDto(category);
    }

    private void validateCategoryDescription(CategoryRequestDto request) {
        if (!StringUtils.hasText(request.getDescription())) {
            throw new BusinessException("Category description is required");
        }
    }
}

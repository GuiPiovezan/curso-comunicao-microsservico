package br.com.cursoudemy.productapi.modules.produto.service;

import br.com.cursoudemy.productapi.config.exception.ValidateException;
import br.com.cursoudemy.productapi.modules.produto.dto.CategoryRequestDto;
import br.com.cursoudemy.productapi.modules.produto.dto.CategoryResponseDto;
import br.com.cursoudemy.productapi.modules.produto.model.Category;
import br.com.cursoudemy.productapi.modules.produto.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryResponseDto save(CategoryRequestDto request) {
        validateCategoryNameInformed(request);
        var category = categoryRepository.save(Category.of(request));
        return CategoryResponseDto.of(category);
    }

    private void validateCategoryNameInformed(CategoryRequestDto request) {
        if (isEmpty(request.getDescription())) {
            throw new ValidateException("The category description was not informed.");
        }
    }
}

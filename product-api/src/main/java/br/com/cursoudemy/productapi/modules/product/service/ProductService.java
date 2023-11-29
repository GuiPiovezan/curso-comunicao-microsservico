package br.com.cursoudemy.productapi.modules.product.service;

import br.com.cursoudemy.productapi.config.exception.ValidateException;
import br.com.cursoudemy.productapi.modules.category.service.CategoryService;
import br.com.cursoudemy.productapi.modules.product.dto.ProductRequestDto;
import br.com.cursoudemy.productapi.modules.product.dto.ProductResponseDto;
import br.com.cursoudemy.productapi.modules.product.model.Product;
import br.com.cursoudemy.productapi.modules.product.repository.ProductRepository;
import br.com.cursoudemy.productapi.modules.supplier.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
public class ProductService {

    private static final Integer ZERO = 0;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private CategoryService categoryService;

    public ProductResponseDto save(ProductRequestDto request) {
        validateProductDataInformed(request);
        validateCategoryAndSupplierInformed(request);

        var category = categoryService.findById(request.getCategoryId());
        var supplier = supplierService.findById(request.getSupplierId());
        var product = productRepository.save((Product.of(request, supplier, category)));

        return ProductResponseDto.of(product);
    }

    private void validateProductDataInformed(ProductRequestDto request) {
        if (isEmpty(request.getName())) {
            throw new ValidateException("The product's name was not informed.");
        }

        if (isEmpty(request.getQuantityAvailable())) {
            throw new ValidateException("The product's quantity was not informed.");
        }

        if (request.getQuantityAvailable() <= ZERO) {
            throw new ValidateException("The quantity should not be less or equal to zero.");
        }
    }

    private void validateCategoryAndSupplierInformed(ProductRequestDto request) {
        if (isEmpty(request.getCategoryId())) {
            throw new ValidateException("The category ID has not informed.");
        }

        if (isEmpty(request.getSupplierId())) {
            throw new ValidateException("The supplier ID has not informed.");
        }
    }
}

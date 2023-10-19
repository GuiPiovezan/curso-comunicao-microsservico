package br.com.cursoudemy.productapi.modules.produto.dto;

import br.com.cursoudemy.productapi.modules.produto.model.Category;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class CategoryResponseDto {
    private Integer id;
    private String description;

    public static CategoryResponseDto of(Category category) {
        var response = new CategoryResponseDto();
        BeanUtils.copyProperties(category, response);

        return response;
    }
}

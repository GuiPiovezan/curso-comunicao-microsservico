package br.com.cursoudemy.productapi.modules.supplier.dto;

import br.com.cursoudemy.productapi.modules.category.model.Category;
import br.com.cursoudemy.productapi.modules.supplier.model.Supplier;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class SupplierResponseDto {
    private Integer id;
    private String description;

    public static SupplierResponseDto of(Supplier supplier) {
        var response = new SupplierResponseDto();
        BeanUtils.copyProperties(supplier, response);
        return response;
    }
}

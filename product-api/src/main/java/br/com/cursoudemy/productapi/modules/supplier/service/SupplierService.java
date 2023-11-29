package br.com.cursoudemy.productapi.modules.supplier.service;

import br.com.cursoudemy.productapi.config.exception.ValidateException;
import br.com.cursoudemy.productapi.modules.supplier.dto.SupplierRequestDto;
import br.com.cursoudemy.productapi.modules.supplier.dto.SupplierResponseDto;
import br.com.cursoudemy.productapi.modules.supplier.model.Supplier;
import br.com.cursoudemy.productapi.modules.supplier.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    public Supplier findById(Integer supplierId) {
        return supplierRepository.findById(supplierId).orElseThrow(() -> new ValidateException("There's no supplier for the given id."));
    }

    public SupplierResponseDto save(SupplierRequestDto request) {
        validateSupplierDescriptionInformed(request);
        var supplier = supplierRepository.save(Supplier.of(request));
        return SupplierResponseDto.of(supplier);
    }

    private void validateSupplierDescriptionInformed(SupplierRequestDto request) {
        if (isEmpty(request.getDescription())) {
            throw new ValidateException("The supplier description was not informed.");
        }
    }
}

package com.diploma.service;

import com.diploma.exception.InvalidProductDataException;
import com.diploma.exception.ProductNotFoundException;
import com.diploma.mapper.Mapper;
import com.diploma.model.Manufacture;
import com.diploma.model.Product;
import com.diploma.model.dto.ManufactureDto;
import com.diploma.repository.ManufactureRepository;
import com.diploma.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ManufactureRepository manufactureRepository;

    @Autowired
    private Mapper mapper;

    public Manufacture addNewProduct(ManufactureDto manufactureDto) {
        Manufacture manufacture = mapper.toEntity(manufactureDto);
        validateProduct(manufacture.getProduct());
        validateManufacture(manufacture);

        productRepository.save(manufacture.getProduct());
        return manufactureRepository.save(manufacture);
    }

    private void validateManufacture(Manufacture manufacture) {
        if (manufacture.getManufacturingTime() == null || manufacture.getManufacturingTime().isBlank()) {
            throw new InvalidProductDataException("Manufacturing time cannot be empty");
        }
    }

    private void validateProduct(Product product) {
        if (product == null) {
            throw new InvalidProductDataException("Product is null");
        }
        if (product.getPrice() == null || product.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidProductDataException("Product price cannot be less than zero");
        }
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            throw new InvalidProductDataException("Product name cannot be empty");
        }
        if (product.getBrand() == null || product.getBrand().trim().isEmpty()) {
            throw new InvalidProductDataException("Product brand cannot be empty");
        }
        if (product.getModel() == null || product.getModel().trim().isEmpty()) {
            throw new InvalidProductDataException("Product model cannot be empty");
        }
    }

    public void deleteProduct(Long productId) throws ProductNotFoundException {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isEmpty()) {
            throw new ProductNotFoundException("The transport with id " + productId + " does not exist");
        }

        productRepository.delete(product.get());
    }
}

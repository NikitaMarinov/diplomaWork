package com.diploma.service;

import com.diploma.exception.InvalidProductDataException;
import com.diploma.exception.ProductNotFoundException;
import com.diploma.mapper.Mapper;
import com.diploma.model.Product;
import com.diploma.model.dto.ProductDto;
import com.diploma.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private Mapper mapper;

    public Product addNewProduct(ProductDto productDto) {
        Product product = mapper.toEntity(productDto);
        validateProduct(product);

        return productRepository.save(product);
    }

    private void validateProduct(Product product) {
        if (product.getPrice() == null || product.getPrice()  < 0) {
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

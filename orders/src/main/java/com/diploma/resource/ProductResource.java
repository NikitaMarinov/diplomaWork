package com.diploma.resource;

import com.diploma.exception.ProductNotFoundException;
import com.diploma.model.Product;
import com.diploma.model.dto.ProductDto;
import com.diploma.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
public class ProductResource {

    @Autowired
    private ProductService productService;

    @PostMapping
    public Product createProduct(@RequestBody ProductDto productDto) {
        return productService.addNewProduct(productDto);
    }

    @DeleteMapping("/{product_id}")
    public void deleteProduct(@PathVariable("product_id") Long productId) throws ProductNotFoundException {
        productService.deleteProduct(productId);
    }
}

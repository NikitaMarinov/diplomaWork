package com.diploma.model.dto;

import com.diploma.model.Product;

public class ManufactureDto {
    String manufacturingTime;
    private ProductDto product;

    public ManufactureDto() {
    }


    public ManufactureDto(String manufacturingTime, ProductDto product) {
        this.manufacturingTime = manufacturingTime;
        this.product = product;
    }

    public String getManufacturingTime() {
        return manufacturingTime;
    }

    public ManufactureDto setManufacturingTime(String manufacturingTime) {
        this.manufacturingTime = manufacturingTime;
        return this;
    }

    public ProductDto getProduct() {
        return product;
    }

    public ManufactureDto setProduct(ProductDto product) {
        this.product = product;
        return this;
    }
}

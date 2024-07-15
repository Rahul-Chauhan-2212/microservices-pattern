package org.pattern.commandservice.service;

import lombok.extern.slf4j.Slf4j;
import org.pattern.commandservice.controller.product.ProductRequest;
import org.pattern.commandservice.controller.product.ProductResponse;
import org.pattern.commandservice.converter.Converter;
import org.pattern.commandservice.domain.product.Product;
import org.pattern.commandservice.domain.product.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    private final Converter converter;

    public ProductService(ProductRepository productRepository, Converter converter) {
        this.productRepository = productRepository;
        this.converter = converter;
    }


    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = (Product) converter.convert(productRequest, new Product());
        product.setProductId(UUID.randomUUID());
        Product savedProduct = productRepository.save(product);
        ProductResponse productResponse = (ProductResponse) converter.convert(savedProduct, new ProductResponse());
        return productResponse;
    }
}

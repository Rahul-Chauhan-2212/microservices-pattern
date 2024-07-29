package org.pattern.commandservice.service;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.pattern.commandservice.command.product.CreateProductCommand;
import org.pattern.commandservice.controller.product.ProductRequest;
import org.pattern.commandservice.controller.product.ProductResponse;
import org.pattern.commandservice.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class ProductService {

    private final CommandGateway commandGateway;

    private final Converter converter;

    public ProductService(CommandGateway commandGateway, Converter converter) {
        this.commandGateway = commandGateway;
        this.converter = converter;
    }

//    private final ProductRepository productRepository;
//
//
//
//    public ProductService(ProductRepository productRepository, Converter converter) {
//        this.productRepository = productRepository;
//        this.converter = converter;
//    }
//
//
//    public ProductResponse createProduct(ProductRequest productRequest) {
//        Product product = (Product) converter.convert(productRequest, new Product());
//        product.setProductId(UUID.randomUUID());
//        Product savedProduct = productRepository.save(product);
//        ProductResponse productResponse = (ProductResponse) converter.convert(savedProduct, new ProductResponse());
//        return productResponse;
//    }

    public ProductResponse createProduct(ProductRequest productRequest) {
        UUID productId = UUID.randomUUID();
        CreateProductCommand createProductCommand = new CreateProductCommand(productId.toString(), productRequest.getProductCode(), productRequest.getProductName(), productRequest.getExtensions());
        commandGateway.send(createProductCommand);
        return (ProductResponse) converter.convert(createProductCommand, new ProductResponse());
    }
}

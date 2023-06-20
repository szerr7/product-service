package edu.miu.productservice.service;


import edu.miu.productservice.dto.ProductRequest;
import edu.miu.productservice.dto.ProductResponse;
import edu.miu.productservice.model.Product;
import edu.miu.productservice.respository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
@Slf4j
public class ProductService {



    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }



    public void createProduct(ProductRequest productRequest){

       Product product  = Product.builder()
               .name(productRequest.getName())
               .description(productRequest.getDescription())
               .price(productRequest.getPrice())
               .build();

         // save product to database
        productRepository.save(product);

        // log the product
        log.info("product {} is saved to database", product.getId());


    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();

        return  products.stream().map(product -> ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build()).toList();

    }

}

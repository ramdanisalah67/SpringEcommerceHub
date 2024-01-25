package com.example.productService.Services;

import com.example.productService.Repositories.ProductRepository;
import com.example.productService.dto.ProductRequest;
import com.example.productService.dto.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.productService.Models.Product ;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class myService {


    private final ProductRepository productRepository ;

    public void createProduct(ProductRequest productRequest){

        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build() ;

        productRepository.save(product);
    }

    public List<ProductResponse> allProduct(){
        return  productRepository.findAll().stream().map(e->convertToProductResponse(e)).collect(Collectors.toList());
    }

   ProductResponse  convertToProductResponse(Product p){
            ProductResponse productResponse = ProductResponse.builder()
                    .id(p.getId())
                    .name(p.getName())
                    .price(p.getPrice())
                    .description(p.getDescription())
                    .build();
            return productResponse ;
    }
}

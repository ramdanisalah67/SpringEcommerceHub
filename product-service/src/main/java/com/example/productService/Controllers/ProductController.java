package com.example.productService.Controllers;

import com.example.productService.Services.myService;
import com.example.productService.dto.ProductRequest;
import com.example.productService.dto.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/product")
@RequiredArgsConstructor
public class ProductController {


    private final myService productService;

    @GetMapping("/welcome")
    private String welcome(){
        return "welcome to Product service";
    }


    @PostMapping("/add")
    private ResponseEntity<Object> createProduct(@RequestBody ProductRequest productRequest){
        productService.createProduct(productRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/all")
    private List<ProductResponse> allProduct(){

        return  productService.allProduct();
    }
}

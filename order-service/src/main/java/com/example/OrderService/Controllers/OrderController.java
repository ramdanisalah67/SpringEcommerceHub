package com.example.OrderService.Controllers;

import com.example.OrderService.Services.myService;
import com.example.OrderService.dto.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/order/")
@RequiredArgsConstructor
public class OrderController {


    private final myService orderService ;

    @PostMapping("placeOrder")
    public ResponseEntity<Object> placeOrder(@RequestBody OrderRequest orderRequest){

        orderService.placeOrder(orderRequest);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}

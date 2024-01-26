package com.example.OrderService.Services;

import com.example.OrderService.Models.Order;
import com.example.OrderService.Models.OrderLineItems;
import com.example.OrderService.Repositories.OrderRepository;
import com.example.OrderService.dto.InventoryResponse;
import com.example.OrderService.dto.OrderLineItemsRequest;
import com.example.OrderService.dto.OrderRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class myService {

    private final OrderRepository orderRepository;


    private final WebClient.Builder webClientBuilder ;
    public void placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsRequest()
                .stream().map(e->convertToOrderLineItems(e)).collect(Collectors.toList());
        order.setOrderLineItems(orderLineItems);
        orderLineItems.forEach(lineItem -> lineItem.setOrder(order));

        //get all skuCode from orderLine Items :
        List<String> skuCodes = orderLineItems.stream().map(e->e.getSkuCode()).toList();

        //call inventory service and place order if product is in stock
        InventoryResponse[] inventoryResponses  =  webClientBuilder.build().get().uri("http://INVENTORY-SERVICE/api/inventory/isInStock",
                        uriBuilder -> uriBuilder.queryParam("skuCodes",skuCodes).build())
                            .retrieve()
                            .bodyToMono(InventoryResponse[].class)
                            .block() ;
        Map<String, InventoryResponse> inventoryResponseMap = Arrays.stream(inventoryResponses)
                .collect(Collectors.toMap(InventoryResponse::getSkuCode, Function.identity()));

        // Check if all order line items' SKU codes are in stock
        boolean allProductInStock = orderLineItems.stream()
                .allMatch(orderLineItem -> {
                    InventoryResponse inventoryResponse = inventoryResponseMap.get(orderLineItem.getSkuCode());
                    return inventoryResponse != null && inventoryResponse.isInStock();
                });
        if (allProductInStock){
            orderRepository.save(order);
            log.info("order Placed Syccessfully !!!");

        }
        else{
            log.error("some of product is not in stock so order operation is failed <><>");

        }
        log.info("SKU Codes from Order Line Items: {}", skuCodes);
        log.info("Inventory Responses: {}", Arrays.toString(inventoryResponses));


    }

    OrderLineItems  convertToOrderLineItems(OrderLineItemsRequest orderLineItemsRequest){
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setQuantity(orderLineItemsRequest.getQuantity());
        orderLineItems.setPrice(orderLineItemsRequest.getPrice());
        orderLineItems.setSkuCode(orderLineItemsRequest.getSkuCode());
        return orderLineItems ;
    }
}

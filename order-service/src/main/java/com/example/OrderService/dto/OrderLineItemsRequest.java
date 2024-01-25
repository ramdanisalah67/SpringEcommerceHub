    package com.example.OrderService.dto;

    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    import java.math.BigDecimal;
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class OrderLineItemsRequest {
        private String skuCode ;
        private BigDecimal price ;
        private Integer quantity ;

    }

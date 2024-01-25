package com.example.OrderService.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "OrderTable")
@Data
@AllArgsConstructor
@NoArgsConstructor

    public class Order {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id ;

        private String orderNumber ;
        @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
        private List<OrderLineItems> orderLineItems;
    }



package com.example.inventoryservice;


import com.example.inventoryservice.Models.Inventory;
import com.example.inventoryservice.Repositories.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Random;
import java.util.UUID;


@SpringBootApplication
public class inventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(inventoryServiceApplication.class, args);


    }


    // @Bean
    public CommandLineRunner loadData(InventoryRepository inventoryRepository) {
        return  args->{
            inventoryRepository.deleteAll();
            for(int i=0;i<10;i++) {
                Inventory inventory = new Inventory();
                inventory.setQuantity(new Random().nextInt(101));
                inventory.setSkuCode(UUID.randomUUID().toString().substring(0,7));
                inventoryRepository.save(inventory);
            }
        };
    }

}

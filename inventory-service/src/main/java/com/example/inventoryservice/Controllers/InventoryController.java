package com.example.inventoryservice.Controllers;

import com.example.inventoryservice.Repositories.InventoryRepository;
import com.example.inventoryservice.Services.InventoryService;
import com.example.inventoryservice.dto.InventoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/inventory")
@RequiredArgsConstructor
public class InventoryController {


    private final InventoryService inventoryService;

    @GetMapping("isInStock")
    @ResponseStatus (HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCodes){
        return  inventoryService.isInStock(skuCodes);
    }
}

package com.example.inventoryservice.Services;

import com.example.inventoryservice.Repositories.InventoryRepository;
import com.example.inventoryservice.dto.InventoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> skuCode){
      return   inventoryRepository.findBySkuCodeIn(skuCode).stream().map(e->
          InventoryResponse.builder().skuCode(e.getSkuCode()).isInStock(e.getQuantity()>0).build()
      ).toList();
    }
}

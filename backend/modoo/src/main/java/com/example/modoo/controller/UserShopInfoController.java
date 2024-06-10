package com.example.modoo.controller;

import com.example.modoo.entity.Store;
import com.example.modoo.repository.StoreRepository;
import com.example.modoo.service.UserEmailLookupService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class UserShopInfoController {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private UserEmailLookupService userEmailLookupService;

    @PostMapping("/update-store-note")
    public ResponseEntity<String> updateStoreNote(@RequestBody Store storeDetails) {
        String userEmail = userEmailLookupService.getCurrentUserEmail();

        Store store = storeRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("Store not found"));

        store.setNote(storeDetails.getNote());
        storeRepository.save(store);

        return ResponseEntity.ok("Store note updated successfully");
    }
}

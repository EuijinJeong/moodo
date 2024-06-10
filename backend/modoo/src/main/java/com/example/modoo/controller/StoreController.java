package com.example.modoo.controller;

import com.example.modoo.entity.Store;
import com.example.modoo.repository.StoreRepository;
import com.example.modoo.service.StoreService;
import com.example.modoo.service.UserEmailLookupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Store Controller 클래스는 Spring Boot 애플리케이션에서 RESTful API를 통해 상점(Store) 정보를 제공하는 역할을 합니다.
 * 이 클래스는 클라이언트로부터 HTTP 요청을 받아, 데이터베이스에서 상점 정보를 조회한 후, 클라이언트에게 응답으로 해당 정보를 반환합니다.
 */

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/stores")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private UserEmailLookupService userEmailLookupService;

    // 현재 사용자의 상점 정보를 조회
    @GetMapping("/current")
    public ResponseEntity<Store> getStoreForCurrentUser() {
        Store store = storeService.getStoreForCurrentUser();
        return ResponseEntity.ok(store);
    }

    // 현재 사용자의 상점 소개글을 업데이트
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


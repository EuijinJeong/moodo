package com.example.modoo.repository;

import com.example.modoo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // 상점 ID로 제품 목록을 가져오는 메서드 추가
    List<Product> findByStoreId(Long storeId);
}

package com.example.modoo.controller;

import com.example.modoo.entity.Product;
import com.example.modoo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * 이 컨트롤러는 주어진 ID로 제품을 조회하는 REST API 엔드포인트를 제공합니다.
 * 클라이언트가 특정 제품 ID를 사용하여 GET 요청을 보내면, 이 컨트롤러는 해당 ID에 대한 제품 정보를 데이터베이스에서 찾아 반환합니다.
 * 제품이 존재하지 않으면 404 Not Found 상태를 반환합니다.
 *
 * 이 컨트롤러를 통해 프론트엔드 애플리케이션이 특정 제품의 상세 정보를 서버에서 가져올 수 있습니다.
 */


// 수정 필요
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            return ResponseEntity.ok(product.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

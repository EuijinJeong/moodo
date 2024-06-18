package com.example.modoo.controller;

import com.example.modoo.dto.ProductDto;
import com.example.modoo.entity.Product;
import com.example.modoo.repository.ProductRepository;
import com.example.modoo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @Autowired
    private ProductService productService;


    // 상점 ID로 제품 목록을 가져오는 엔드포인트 추가 (여기에서 문제 발생)
    @GetMapping("products/store/{storeId}")
    public ResponseEntity<List<ProductDto>> getProductsByStoreId(@PathVariable Long storeId){
        List<ProductDto> products = productService.getProductsByStoreId(storeId);
        return ResponseEntity.ok(products);
    }

    // 제품 ID로 제품 정보를 가져오는 엔드포인트
    @GetMapping("products-detail/{productId}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long productId) {
        System.out.println("Fetching product with ID: " + productId);
        ProductDto productDto = productService.getProductById(productId);
        if (productDto != null) {
            return ResponseEntity.ok(productDto);
        } else {
            System.out.println("Product with ID " + productId + " not found");
            return ResponseEntity.notFound().build();
        }
    }

}

package com.example.modoo.controller;

import com.example.modoo.entity.Product;
import com.example.modoo.service.ProductService;
import com.example.modoo.dto.ProductDto;
import com.example.modoo.service.UserEmailLookupService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductRegisterController {

    @Autowired
    private ProductService productService;
    private final UserEmailLookupService userEmailLookupService;

    @PostMapping("/productRegistration") // api 엔드포인트 지정
    public ResponseEntity<Product> saveProduct(@RequestPart("product") ProductDto productDto,
                                                 @RequestPart("files") List<MultipartFile> files) {

        String email = userEmailLookupService.getCurrentUserEmail();
        System.out.println("사용자 이메일 주소: " + email);

        // 상픔 저장
        Product savedProduct = productService.saveProduct(productDto, files, email);
        return ResponseEntity.ok(savedProduct);
    }

}

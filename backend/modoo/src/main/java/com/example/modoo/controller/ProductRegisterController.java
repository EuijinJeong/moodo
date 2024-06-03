package com.example.modoo.controller;

import com.example.modoo.entity.Product;
import com.example.modoo.service.ProductService;
import com.example.modoo.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.Console;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductRegisterController {

    @Autowired
    private ProductService productService;

    @PostMapping("/productRegistration") // api 엔드포인트 지정
    public ResponseEntity<Product> saveProduct(@RequestPart("product") ProductDto productDto,
                                                 @RequestPart("files") List<MultipartFile> files) {

        // 현재 인증된 사용자 이메일 정보를 가져옴
        String email = getCurrentUserEmail().orElseThrow( () -> new IllegalStateException("현재 로그인된 사용자 정보를 찾을 수 없습니다."));
        System.out.println("사용자 이메일 주소: " + email); // email 대신 userid를 반환하여 오류가 발생함.

        // 상픔 저장
        Product savedProduct = productService.saveProduct(productDto, files, email);
        return ResponseEntity.ok(savedProduct);
    }
    private Optional<String> getCurrentUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return Optional.ofNullable(userDetails.getUsername());
        }
        return Optional.empty();
    }

}

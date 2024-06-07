package com.example.modoo.service;

import com.example.modoo.dto.ProductDto;
import com.example.modoo.entity.Member;
import com.example.modoo.entity.Product;
import com.example.modoo.repository.MemberRepository;
import com.example.modoo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private FileService fileService;
    public Product saveProduct(ProductDto productDto, List<MultipartFile> files, String email) {
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setCondition(productDto.getCondition());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setAcceptOffers(productDto.getAcceptOffers());

        // 이 부분 로직 다시 검토해야함
        List<String> fileUrls = fileService.saveFiles(files);
        product.setFileUrls(fileUrls);

        // 데이터베이스의 users 테이블에서 현재 로그인한 사용자의 email이 있는지 조회 없으면 예외처리
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Member 엔티티에서 해당하는 email을 찾을 수 없습니다: " + email));
        // 현재 로그인한 사용자의 email이 데이터베이스에 존재한다면 product테이블에 users테이블과 product테이블과 조인을 위해 email 정보 추가
        product.setMember(member);

        Product savedProduct = productRepository.save(product);
        fileService.saveFiles(files);
        return  savedProduct;
    }
}

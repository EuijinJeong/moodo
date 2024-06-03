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

        // Find member by email and set to product
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Member 엔티티에서 해당하는 email을 찾을 수 없습니다: " + email)); // 여기서 오류 발생
        product.setMember(member);

        Product savedProduct = productRepository.save(product);
        fileService.saveFiles(files);
        return  savedProduct;
    }
}

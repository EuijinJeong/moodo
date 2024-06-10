package com.example.modoo.service;

import com.example.modoo.dto.ProductDto;
import com.example.modoo.entity.Member;
import com.example.modoo.entity.Product;
import com.example.modoo.entity.Store;
import com.example.modoo.repository.MemberRepository;
import com.example.modoo.repository.ProductRepository;
import com.example.modoo.repository.StoreRepository;
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
    private StoreRepository storeRepository;

    @Autowired
    private UserEmailLookupService userEmailLookupService;

    @Autowired
    private FileService fileService;
    public Product saveProduct(ProductDto productDto, List<MultipartFile> files) {

        // 현재 인증된 사용자의 이메일 가져오기
        String userEmail = userEmailLookupService.getCurrentUserEmail();

        // userEmail을 이용해 Member를 조회
        Member member = memberRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("Member 엔티티에서 해당하는 email을 찾을 수 없습니다: " + userEmail));

        // Member를 이용해 Store를 조회
        Store store = storeRepository.findByMemberId(member.getId())
                .orElseThrow(() -> new RuntimeException("Store 엔티티에서 해당하는 member를 찾을 수 없습니다: " + member.getId()));

        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setCondition(productDto.getCondition());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setAcceptOffers(productDto.getAcceptOffers());
        product.setStore(store);

        // 파일 저장 로직: 이 부분 로직 다시 검토해야함
        List<String> fileUrls = fileService.saveFiles(files);
        product.setFileUrls(fileUrls);

        Product savedProduct = productRepository.save(product);
        fileService.saveFiles(files);

        return  savedProduct;
    }
}

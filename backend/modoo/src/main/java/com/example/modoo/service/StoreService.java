package com.example.modoo.service;

import com.example.modoo.entity.Member;
import com.example.modoo.entity.Store;
import com.example.modoo.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    @Transactional
    public Store createStore(Member member) {

        // 사용자당 하나의 상점만 생성이 되도록 로직 아래에 추가
        if (storeRepository.existsByEmail(member.getEmail())) {
            throw new RuntimeException("이미 상점이 생성된 사용자입니다");
        }

        Store store = new Store();
        store.setEmail(member.getEmail());
        store.setName(member.getStudentId()); // 상점 이름 초기값으로 학번 설정
        store.setNote("상점 소개글을 입력해 주세요.");
        store.setMember(member); // 여기서 문제가 발생하는

        // Member 엔티티에도 Store 설정
        member.setStore(store);

        return storeRepository.save(store);
    }
}

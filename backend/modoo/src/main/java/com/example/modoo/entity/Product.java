package com.example.modoo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 애플리케이션에서 사용되는 데이터의 구조를 정의
 */
@Table(name = "product")
@Getter
@Setter
@Entity
public class Product {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long id;

    @Column( name = "title", nullable = false)
    private String title;

    @Column( name = "product_condition", nullable = false)
    private String condition;

    @Column ( name = "description")
    private String description;

    @Column ( name = "price", nullable = false)
    private double price;

    @Column ( name = "accept_offers")
    private boolean acceptOffers;


    @ElementCollection // 별도의 테이블에 파일 url 저장
    private List<String> fileUrls;

    @ManyToOne // 이 부분 수정해야할 수도 OneToMany일 수도 있음
    @JoinColumn(name = "member_email", referencedColumnName = "email")
    private Member member;

}

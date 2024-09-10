package com.example.modoo.controller;

import com.example.modoo.dto.AddressDto;
import com.example.modoo.entity.MemberAddress;
import com.example.modoo.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class AddressController {
    @Autowired
    private AddressService addressService;

    /**
     * 사용자의 주소 정보를 저장하는 역할을 컨트롤하는 컨트롤 메소드
     *
     * @param memberId
     * @param addressDto
     * @return
     */
    @PostMapping("/members/{memberId}/addresses")
    public ResponseEntity<MemberAddress> addMemberAddress (@PathVariable Long memberId, @RequestBody AddressDto addressDto) {
        MemberAddress saveMemberAddress = addressService.saveMemberAddress(memberId, addressDto);

        return ResponseEntity.ok(saveMemberAddress);
    }

    /**
     * 사용자의 주소 정보를 업데이트하는 역할을 컨트롤하는 컨트롤 메소드
     *
     * @param addressId
     * @param addressDto
     * @return
     */
    @PutMapping("/addresses/{addressId}")
    public ResponseEntity<MemberAddress> updateMemberAddress (@PathVariable Long addressId, @RequestBody AddressDto addressDto) {
        MemberAddress updateMemberAddress = addressService.updateMemberAddress(addressId, addressDto);

        return ResponseEntity.ok(updateMemberAddress);
    }
}
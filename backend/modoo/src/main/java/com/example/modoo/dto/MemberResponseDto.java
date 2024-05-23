package com.example.modoo.dto;

import com.example.modoo.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MemberResponseDto {
    private String email;

    public static MemberResponseDto of(Member member) {
        return MemberResponseDto.builder()
                .email(member.getEmail())
                .build();
    }
}

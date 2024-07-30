package com.example.modoo.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ChatRoomDto {
    private Long sender;
    private Long receiver;
    private String lastMessage;
    private LocalDateTime lastMessageTime;
    private List<String> chatMessageList; // 해당 유저가 생성한 chatRoomList 목록
}

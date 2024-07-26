package com.example.modoo.controller;

import com.example.modoo.dto.ChatRoomDto;
import com.example.modoo.service.ChatService;
import com.example.modoo.service.UserEmailLookupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @Autowired
    UserEmailLookupService userEmailLookupService;

    @PostMapping("/go-to-chatroom/{storeId}")
    @MessageMapping("/chat.sendMessage")
    public ResponseEntity<ChatRoomDto> gotoChatRoom(@PathVariable Long storeId) {

        // 사용자의 이메일 정보를 가져옴
        String userEmail = userEmailLookupService.getCurrentUserEmail();

        // 사용자의 이메일정보를 서비스계층에 전달 ( 반환타입이 Dto임.)
        ChatRoomDto chatRoomDto = chatService.findOrCreateChatRoom(userEmail, storeId);

        // 채팅방 정보를 클라이언트에게 반환
        return ResponseEntity.ok(chatRoomDto);
    }

    // TODO: 페이지 이동은 되는데, 리스트 형태로 화면에 안보여짐. 이거 수정해야 함.

}

package com.example.modoo.service;

import com.example.modoo.dto.ChatRoomDto;
import com.example.modoo.dto.MemberResponseDto;
import com.example.modoo.entity.ChatMessage;
import com.example.modoo.entity.ChatRoom;
import com.example.modoo.entity.Member;
import com.example.modoo.entity.Store;
import com.example.modoo.repository.ChatMessageRepository;
import com.example.modoo.repository.ChatRoomRepository;
import com.example.modoo.repository.MemberRepository;
import com.example.modoo.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChatService {

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private StoreRepository storeRepository;




    /**
     *
     * @param userEmail : 현재 로그인한 사용자 ( sender )
     * @param storeId : 사용자가 메세지를 보내는 사람의 상점 아이디 값 ( receiver )
     * @return
     */
    public ChatRoomDto findOrCreateChatRoom(String userEmail, long storeId) {

        // 이메일을 통해 사용자 정보 조회
        Member sender = memberRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("Member 엔티티에서 해당하는 email을 찾을 수 없습니다: " + userEmail));

        // StoreId를 통해 상점 조회
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("해당 상점의 판매자를 찾을 수 없습니다: " + storeId));

        // 상점 정보로 사용자 조회
        Member receiver = store.getMember();

        // 두 사용자 간의 채팅방 조회
        Optional<ChatRoom> chatRoomOptional = chatRoomRepository.findBySenderIdAndReceiverId(sender.getId(), receiver.getId());

        ChatRoom chatRoom;

        if(chatRoomOptional.isPresent()) {
            // 채팅방이 존재하는 경우
            // 기존 채팅방을 가져와서 반환한다.
            chatRoom = chatRoomOptional.get();
        } else {
            // 새로운 채팅방 생성함
            chatRoom = new ChatRoom();
            chatRoom.setSenderId(sender.getId());
            chatRoom.setReceiverId(receiver.getId());
            chatRoom = chatRoomRepository.save(chatRoom);
        }

        // DTO로 변환하여 반환함.
        return convertToDto(chatRoom);
    }

    /**
     *
     * @param email:
     * @return
     */
    public List<ChatRoomDto> getUserChatRooms(String email) {
        // 현재 로그인한 사용자의 정보
        Member user = memberRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("해당 사용자의 이메일을 찾을 수 없습니다." + email));

        List<ChatRoom> chatRooms = chatRoomRepository.findBySenderId(user.getId());
        return chatRooms.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    /**
     *
     * @param chatRoom 엔티티 클래스
     * @return 엔티티 클래스에 있는 정보를 Dto 객체에 담아 리턴함.
     */
    public ChatRoomDto convertToDto(ChatRoom chatRoom) {
        ChatRoomDto chatRoomDto = new ChatRoomDto();
        chatRoomDto.setReceiver(chatRoom.getReceiverId());
        chatRoomDto.setSender(chatRoom.getSenderId());
        chatRoomDto.setLastMessage(chatRoom.getLastMessage());
        chatRoomDto.setLastMessageTime(chatRoom.getLastMessageTime());

        return  chatRoomDto;
    }
}

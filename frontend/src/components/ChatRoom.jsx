import React, {useEffect, useState} from 'react';
import axios from "axios";
import "../css/chat-room.css";
import ChatMessageInput from "./ChatMessageInput";
import ChatMessageBubble from "./ChatMessageBubble";

const ChatRoom = ({roomId}) => {
    const [chatRoom, setChatRoom] = useState(null);
    const [messages, setMessages] = useState([]);

    useEffect(() => {
        if (roomId) {
            const fetchChatRoomDetails = async () => {
                try {
                    const token = localStorage.getItem('token');
                    const response = await axios.get(`/api/chat-rooms/${roomId}`, {
                        headers: {
                            Authorization: `Bearer ${token}`
                        }
                    });

                    console.log("Chat room response: ", response.data); // 데이터 구조 확인

                    setChatRoom(response.data); // 여기서 응답 데이터 전체를 사용합니다
                    setMessages(response.data.chatMessageList || []); // chatMessageList가 null이면 빈 배열 사용
                } catch (error) {
                    console.error("ChatRoom을 가져오는 과정에서 서버상 오류가 발생했습니다.", error);
                }
            };
            fetchChatRoomDetails();
        }
    }, [roomId]);

    const handleSendMessage = async (messageContent) => {
        try {
            const token = localStorage.getItem('token');
            const response = await axios.post(`/api/chat-rooms/${roomId}/messages`,
                {messageContent}, // messageContent만 보냄
                {
                    headers: {
                        Authorization: `Bearer ${token}`
                    }
                });
            // 메시지가 성공적으로 전송된 후, messages 상태를 업데이트합니다.
            setMessages([...messages, response.data]);
        } catch (error) {
            console.error("메세지 전송 중 오류가 발생했습니다.", error);
        }
    };

    if (!chatRoom) {
        return <div>Loading ...</div>;
    } else if (messages.length === 0) {
        return (
            <div>
                <div>No messages in this chat room.</div>
                <ChatMessageInput onSendMessage={handleSendMessage} />  {/* 메시지를 보낼 수 있도록 입력 폼 표시 */}
            </div>
        );
    }

    return (
        <div className="chat-room">
            <div className="messages">
                {messages.map((message, index) => (
                    <ChatMessageBubble
                        key={index}
                        message={message.messageContent}
                        isOwnMessage={message.senderId === '현재 사용자 ID'} // 실제 사용자 ID와 비교
                    />
                ))}
            </div>
            <ChatMessageInput onSendMessage={handleSendMessage} /> {/* 여기서 prop 전달 */}
        </div>
    );
};

export default ChatRoom;
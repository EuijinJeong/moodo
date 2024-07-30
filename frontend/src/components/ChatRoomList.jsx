import React, {useEffect, useState} from 'react';
import ChatRoomListItem from "./ChatRoomListItem";
import axios from "axios";

const ChatRoomList = ({ messages = [], setSelectedChat }) => {
    const [chatRooms, setChatRooms] = useState([]);

    // TODO: 서버에 채팅룸 리스트 불러오는 요청 보내는 메소드 작성해야 함.
    useEffect(() => {
        const fetchChatRooms = async () => {
            try {
                const token = localStorage.getItem('token');
                const response = await axios.get('/api/chat-room-lists', {
                    headers: {
                        Authorization: `Bearer ${token}`
                    }
                });
                setChatRooms(response.data);
            } catch (error) {
                console.error("Chatroom List를 서버에서 불러오는 과정에서 오류가 발생했습니다.", error);
            }
        }

        fetchChatRooms();
    }, []);
    
    return (
        <div className="chat-list">
            <h2>전체 대화</h2>
            {chatRooms.map((msg, index) => (
                <ChatRoomListItem key={index} message={msg} onClick={() => setSelectedChat(msg)} />
            ))}
        </div>
    );
};

export default ChatRoomList;
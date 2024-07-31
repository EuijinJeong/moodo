import React, {useEffect, useState} from 'react';
import axios from "axios";
import {Link} from "react-router-dom";

const ChatRoomList = ({ storeId , setSelectedChat }) => {
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
            {chatRooms.map((room) => (
                <Link to={`/chat-room/${storeId}/${room.id}`} key={room.id}>
                    <div className="chat-list-item">
                        <div className="chat-user">사용자명: {room.sender}</div>
                        <div className="chat-preview">대화 미리보기: {room.lastMessage}</div>
                        <div className="chat-preview">대화 시간: {new Date(room.lastMessageTime).toLocaleDateString()}</div>
                    </div>
                </Link>
            ))}
        </div>
    );
};

export default ChatRoomList;
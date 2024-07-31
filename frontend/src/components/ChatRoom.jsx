import React, {useEffect, useState} from 'react';
import axios from "axios";
import "../css/chat-room.css";

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
                    setChatRoom(response.data.chatRoom);
                    setMessages(response.data.messages);
                } catch (error) {
                    console.error("ChatRoom을 가져오는 과정에서 서버상 오류가 발생했습니다.", error);
                }
            };
            fetchChatRoomDetails();
        }
    }, [roomId]);


    if(!chatRoom) {
        return <div>Loading ...</div>;
    }

    return (
        <div>
            <h2>ChatRoom</h2>
            <div>
                {messages.map((message, index) => (
                    <div key={index} className="message">
                        <div className="sender">{message.sender}</div>
                        <div className="content">{message.messageContent}</div>
                        <div className="timestamp">{new Date(message.timestamp).toLocaleString()}</div>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default ChatRoom;
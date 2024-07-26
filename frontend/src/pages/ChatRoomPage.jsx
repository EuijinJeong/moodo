import React from 'react';
import UserDashboardHeader from "../components/UserDashboardHeader";
import ChatRoomList from "../components/ChatRoomList";
import ChatRoom from "../components/ChatRoom";
import {useParams} from "react-router-dom";

const ChatRoomPage = () => {
    const { storeId } = useParams();

    return (
        <div>
            <UserDashboardHeader />
            <ChatRoomList storeId={storeId}/>
            <ChatRoom />
            <p>채팅룸 화면입니다.</p>
        </div>
    );
};

export default ChatRoomPage;
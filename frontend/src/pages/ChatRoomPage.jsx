import React from 'react';
import UserDashboardHeader from "../components/UserDashboardHeader";
import ChatRoomList from "../components/ChatRoomList";
import ChatRoom from "../components/ChatRoom";
import {useParams} from "react-router-dom";
import ChatRoomWrapper from "../components/ChatRoomWrapper";

const ChatRoomPage = () => {
    const { storeId } = useParams();

    return (
        <div>
            <UserDashboardHeader />
            <ChatRoomWrapper storeId={storeId}/>
            <ChatRoom />
            <p>채팅룸 화면입니다.</p>
        </div>
    );
};

export default ChatRoomPage;
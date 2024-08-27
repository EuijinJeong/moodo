import React from 'react';
import UserDashboardHeader from "../components/UserDashboardHeader";
import ChatRoomList from "../components/ChatRoomList";
import ChatRoom from "../components/ChatRoom";
import {useParams} from "react-router-dom";
import ChatRoomWrapper from "../components/ChatRoomWrapper";

const ChatRoomPage = () => {
    const { storeId , roomId} = useParams();

    return (
        <div>
            <UserDashboardHeader />
            <ChatRoomWrapper storeId={storeId} roomId={roomId} />
        </div>
    );
};

export default ChatRoomPage;
import React from 'react';
import UserDashboardHeader from "../components/UserDashboardHeader";
import UserShopInfo from "../components/UserShopInfo";
import MyShopTap from "../components/MyShopTap";

const MyShopPage = () => {
    return (
        <div>
            <UserDashboardHeader />
            <UserShopInfo />
            <MyShopTap />
        </div>
    );
};

export default MyShopPage;
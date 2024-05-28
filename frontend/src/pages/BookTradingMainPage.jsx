import React from 'react';
import UserDashboardHeader from '../components/UserDashboardHeader';
import ProductRecommendation from '../components/ProductRecommendation';
import Sidebar from '../components/Sidebar';
import '../css/book_trading_mainpage.css';

const BookTradingMainPage = () => {
    return (
        <div className="main-page">
            <UserDashboardHeader />
            <div className="content">
                <Sidebar />
                <ProductRecommendation />
            </div>
        </div>
    );
}

export default BookTradingMainPage;
